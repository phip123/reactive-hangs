package com.example.reactivehangs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class Generator {

    private static final Logger LOG = LoggerFactory.getLogger(Generator.class);
    private final PersonRepository personRepository;

    public Generator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void setup() {
        personRepository.deleteAll().block();
        LOG.info("Never happens");
    }

    @PostConstruct
    public void setup2() {
        List<Person> personList = new LinkedList<>();
        for (int i = 0; i < 200; i++) {
            personList.add(Person.PersonBuilder.aPerson().uuid(UUID.randomUUID()).name("Name " + i).build());
        }

        personRepository.saveAll(personList).blockLast();
    }
}
