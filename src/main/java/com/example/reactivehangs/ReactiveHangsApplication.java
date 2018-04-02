package com.example.reactivehangs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class ReactiveHangsApplication {

    private static final Logger LOG = LoggerFactory.getLogger(ReactiveHangsApplication.class);


    @Bean
    CommandLineRunner commandLineRunner(PersonRepository personRepository) {
        return args -> {
            personRepository.deleteAll().block();
            LOG.info("Deleted all persons");
            List<Person> personList = new LinkedList<>();
            for (int i = 0; i < 200; i++) {
                personList.add(Person.PersonBuilder.aPerson().uuid(UUID.randomUUID().toString()).name("Name " + i).build());
            }
            LOG.info("Start adding person");
            personRepository.saveAll(personList).blockLast();
            LOG.info("Saved all persons");
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveHangsApplication.class, args);
    }
}
