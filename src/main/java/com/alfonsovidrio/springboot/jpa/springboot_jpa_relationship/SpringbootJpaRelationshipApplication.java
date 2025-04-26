package com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.entities.ClientDetails;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.entities.Course;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.entities.Student;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.repositories.ClientDetailsRepository;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.repositories.CourseRepository;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.repositories.StudentRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;
	@Autowired
	private ClientDetailsRepository clientDetailsRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		manyToManyRemoveBidirectionalFind();
		// manyToManyBidirectionalFind();
		// manyToManyBidirectionalRemove();
		// manyToManyBidirectional();
		// manyToManyRemoveFind();
		// manyToManyFind();
		// manyToMany();
		// oneToOneBidirectionalFindById();
		// oneToOneBidirectional();
		// oneToOneFindById();
		// oneToOne();
		// removeInvoiceBidirectionalFindById();
		// oneToManyInvoiceBidirectionalFindById();
		// oneToManyInvoiceBidirectional();
		// removeAddressById(2L);
		// removeAddress();
		// oneToManyFindById(2L);
		// oneToMany();
		// manyToOneFindByIdClient(1L);
		// manyToOne();
	}

	@Transactional
	public void manyToManyRemoveBidirectionalFind() {
		Student student1 = studentRepository.findOneWithCourses(1L).orElseThrow(() -> new RuntimeException("Student not found"));
		Student student2 = studentRepository.findOneWithCourses(2L).orElseThrow(() -> new RuntimeException("Student not found"));

		Course course1 = courseRepository.findOneWithStudents(1L).orElseThrow(() -> new RuntimeException("Course not found"));
		Course course2 = courseRepository.findOneWithStudents(2L).orElseThrow(() -> new RuntimeException("Course not found"));

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1).addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		Optional<Student> optionalStudent1 = studentRepository.findOneWithCourses(1L);
		if (optionalStudent1.isPresent()) {
			Student student = optionalStudent1.orElseThrow(() -> new RuntimeException("Student not found"));
			Course courseToRemove = courseRepository.findOneWithStudents(2L).orElseThrow(() -> new RuntimeException("Course not found"));
			student.removeCourse(courseToRemove);
			studentRepository.save(student);
			System.out.println("Course removed from student: " + student);
		}
	}

	@Transactional
	public void manyToManyBidirectionalFind() {
		Student student1 = studentRepository.findOneWithCourses(1L).orElseThrow(() -> new RuntimeException("Student not found"));
		Student student2 = studentRepository.findOneWithCourses(2L).orElseThrow(() -> new RuntimeException("Student not found"));

		Course course1 = courseRepository.findOneWithStudents(1L).orElseThrow(() -> new RuntimeException("Course not found"));
		Course course2 = courseRepository.findOneWithStudents(2L).orElseThrow(() -> new RuntimeException("Course not found"));

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1).addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	@Transactional
	public void manyToManyBidirectionalRemove() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Java Programming", "Andres");
		Course course2 = new Course("Spring Boot", "Luis");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
		
		Optional<Student> optionalStudent1 = studentRepository.findOneWithCourses(1L);
		if (optionalStudent1.isPresent()) {
			Student student = optionalStudent1.orElseThrow(() -> new RuntimeException("Student not found"));
			Course courseToRemove = courseRepository.findOneWithStudents(2L).orElseThrow(() -> new RuntimeException("Course not found"));
			student.removeCourse(courseToRemove);
			studentRepository.save(student);
			System.out.println("Course removed from student: " + student);
		}
	}

	@Transactional
	public void manyToManyBidirectional() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Java Programming", "Andres");
		Course course2 = new Course("Spring Boot", "Luis");

		// student1.setCourses(Set.of(course1, course2));
		// student2.setCourses(Set.of(course2));

		student1.addCourse(course1).addCourse(course2);
		student2.addCourse(course2);

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}
	
	@Transactional
	public void manyToManyRemoveFind() {
		Student student1 = studentRepository.findById(1L).orElseThrow(() -> new RuntimeException("Student not found"));
		Student student2 = studentRepository.findById(2L).orElseThrow(() -> new RuntimeException("Student not found"));

		Course course1 = courseRepository.findById(1L).orElseThrow(() -> new RuntimeException("Course not found"));
		Course course2 = courseRepository.findById(2L).orElseThrow(() -> new RuntimeException("Course not found"));

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);

		// remove course 2 from student 1
		Optional<Student> optionalStudent1 = studentRepository.findOneWithCourses(1L);
		if (optionalStudent1.isPresent()) {
			Student student = optionalStudent1.orElseThrow(() -> new RuntimeException("Student not found"));	
			
			Optional<Course> optionalCourse2 = courseRepository.findById(2L);
			if (optionalCourse2.isPresent()) {
				Course course = optionalCourse2.orElseThrow(() -> new RuntimeException("Course not found"));
				student.getCourses().remove(course);
				studentRepository.save(student);
				System.out.println("Course removed from student: " + student);
			}
		}
	}

	@Transactional
	public void manyToManyFind() {
		Student student1 = studentRepository.findById(1L).orElseThrow(() -> new RuntimeException("Student not found"));
		Student student2 = studentRepository.findById(2L).orElseThrow(() -> new RuntimeException("Student not found"));

		Course course1 = courseRepository.findById(1L).orElseThrow(() -> new RuntimeException("Course not found"));
		Course course2 = courseRepository.findById(2L).orElseThrow(() -> new RuntimeException("Course not found"));

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	public void manyToMany() {
		Student student1 = new Student("Jano", "Pura");
		Student student2 = new Student("Erba", "Doe");

		Course course1 = new Course("Java Programming", "Andres");
		Course course2 = new Course("Spring Boot", "Luis");

		student1.setCourses(Set.of(course1, course2));
		student2.setCourses(Set.of(course2));

		studentRepository.saveAll(Set.of(student1, student2));

		System.out.println(student1);
		System.out.println(student2);
	}

	public void oneToOneBidirectionalFindById() {
		Optional<Client> optionalClient = clientRepository.findOne(2L);
		optionalClient.ifPresent(c -> {
			ClientDetails clientDetails = new ClientDetails(true,500);

			c.setClientDetails(clientDetails);
	
			clientRepository.save(c);
			System.out.println(c);
		});

	}

	public void oneToOneBidirectional() {
		Client client = new Client("Luis","Vidrio");
		ClientDetails clientDetails = new ClientDetails(true,500);

		client.setClientDetails(clientDetails);

		clientRepository.save(client);
		System.out.println(client);
	}

	public void oneToOneFindById() {
		ClientDetails clientDetails = new ClientDetails(true,500);
		clientDetailsRepository.save(clientDetails);

		Optional<Client> clientOptional = clientRepository.findOne(2L);
		clientOptional.ifPresent(c -> {
			c.setClientDetails(clientDetails);
			clientRepository.save(c);
			System.out.println(c);
		});
	}

	public void oneToOne() {
		ClientDetails clientDetails = new ClientDetails(true,500);
		clientDetailsRepository.save(clientDetails);

		Client client = new Client("Luis","Vidrio");
		client.setClientDetails(clientDetails);
		clientRepository.save(client);
	}

	@Transactional
	public void removeInvoiceBidirectionalFindById() {
		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {
			Invoice invoice1 = new Invoice("office supplies", 2000L);
			Invoice invoice2 = new Invoice("hardware", 5000L);
	
			client.addInvoice(invoice1).addInvoice(invoice2);
	
			System.out.println(clientRepository.save(client));
		});

		// remove invoice
		Optional<Client> optionalClientDb = clientRepository.findOne(1L);
		optionalClientDb.ifPresent(client -> {
			Invoice invoice3 = new Invoice("office supplies", 2000L);
			invoice3.setId(1L);

			Optional<Invoice> optionalInvoice = Optional.of(invoice3);//invoiceRepository.findById(2L);
			optionalInvoice.ifPresent(invoice -> {
				client.removeInvoice(invoice);
				clientRepository.save(client);
				System.out.println(client);
			});
		});
	}

	@Transactional
	public void oneToManyInvoiceBidirectionalFindById() {
		Optional<Client> optionalClient = clientRepository.findOne(1L);
		optionalClient.ifPresent(client -> {
			Invoice invoice1 = new Invoice("office supplies", 2000L);
			Invoice invoice2 = new Invoice("hardware", 5000L);
	
			client.addInvoice(invoice1).addInvoice(invoice2);
	
			System.out.println(clientRepository.save(client));
		});
	}

	@Transactional
	public void oneToManyInvoiceBidirectional() {
		Client client = new Client("Fran", "Moras");

		Invoice invoice1 = new Invoice("office supplies", 2000L);
		Invoice invoice2 = new Invoice("hardware", 5000L);

		client.addInvoice(invoice1).addInvoice(invoice2);

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void removeAddressById(Long id) {
		Optional<Client> optionalClient = clientRepository.findById(id);
		optionalClient.ifPresentOrElse(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 4892);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);
			client.setAddresses(addresses);

			System.out.println(clientRepository.save(client));

			Optional<Client> optionalClient2 = clientRepository.findOneWithAddresses(2L);
			optionalClient2.ifPresent(c -> {
				Address addressToRemove = c.getAddresses()
						.stream()
						.filter(a -> a.getStreet().equals("El verjel"))
						.findFirst()
						.orElse(null);
				c.getAddresses().remove(addressToRemove);
				clientRepository.save(c);
				System.out.println("address deleted: " + addressToRemove);
			});

		}, () -> System.out.println("Client not found by id " + id));
	}

	@Transactional
	public void removeAddress() {
		Client client = new Client("Fran", "Moras");
		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 4892);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);
		System.out.println(client);

		Optional<Client> optionalClient = clientRepository.findOneWithAddresses(4L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println("client: " + c);
		});
	}

	@Transactional
	public void oneToManyFindById(Long id) {
		Optional<Client> optionalClient = clientRepository.findById(id);
		optionalClient.ifPresentOrElse(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 4892);

			Set<Address> addresses = new HashSet<>();
			addresses.add(address1);
			addresses.add(address2);

			clientRepository.save(client);
			System.out.println(client);
		}, () -> System.out.println("Client not found by id " + id));
	}

	@Transactional
	public void oneToMany() {
		Client client = new Client("Fran", "Moras");

		Address address1 = new Address("El verjel", 1234);
		Address address2 = new Address("Vasco de Gama", 4892);

		client.getAddresses().add(address1);
		client.getAddresses().add(address2);

		clientRepository.save(client);
		System.out.println(client);
	}

	@Transactional
	public void manyToOne() {
		Client client = new Client("Santiago", "Díaz");
		clientRepository.save(client);

		Invoice invoice = new Invoice("Office supplies", 2000L);
		invoice.setClient(client);
		Invoice invoiceDB = invoiceRepository.save(invoice);
		System.out.println(invoiceDB);
	}

	@Transactional
	public void manyToOneFindByIdClient(Long id) {
		clientRepository.findById(id).ifPresentOrElse(c -> {
			Invoice invoice = new Invoice("Office supplies", 2000L);
			invoice.setClient(c);
			Invoice invoiceDB = invoiceRepository.save(invoice);
			System.out.println(invoiceDB);
		}, () -> System.out.println("Client not found by id " + id));

	}

}
