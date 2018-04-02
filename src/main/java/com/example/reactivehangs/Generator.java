package com.example.reactivehangs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Generator {

    private static final Logger LOG = LoggerFactory.getLogger(Generator.class);
    private final PersonRepository personRepository;

    public Generator(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostConstruct
    public void setup() {
        personRepository.deleteAll().block();
        LOG.info("Never happens");
    }
}
