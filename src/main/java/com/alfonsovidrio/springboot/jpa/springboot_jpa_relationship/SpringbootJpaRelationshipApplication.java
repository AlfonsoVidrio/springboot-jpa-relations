package com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.entities.Address;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.entities.Client;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.entities.Invoice;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.repositories.ClientRepository;
import com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship.repositories.InvoiceRepository;

@SpringBootApplication
public class SpringbootJpaRelationshipApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private InvoiceRepository invoiceRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaRelationshipApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		removeAddress();
		// oneToManyFindById(2L);
		// oneToMany();
		// manyToOneFindByIdClient(1L);
		// manyToOne();
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

		Optional<Client> optionalClient = clientRepository.findById(4L);
		optionalClient.ifPresent(c -> {
			c.getAddresses().remove(address1);
			clientRepository.save(c);
			System.out.println("client e: " + c);
		});
	}

	@Transactional
	public void oneToManyFindById(Long id) {
		Optional<Client> optionalClient = clientRepository.findById(id);
		optionalClient.ifPresentOrElse(client -> {
			Address address1 = new Address("El verjel", 1234);
			Address address2 = new Address("Vasco de Gama", 4892);

			client.setAddresses(Arrays.asList(address1, address2));

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
