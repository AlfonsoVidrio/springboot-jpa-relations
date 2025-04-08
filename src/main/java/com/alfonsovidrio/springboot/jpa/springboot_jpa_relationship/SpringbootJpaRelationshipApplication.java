package com.alfonsovidrio.springboot.jpa.springboot_jpa_relationship;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

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
		manyToOneFindByIdClient(1L);
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
