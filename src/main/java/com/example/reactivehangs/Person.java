package com.example.reactivehangs;

import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Person {

    @Id
    private UUID uuid;
    private String name;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public static final class PersonBuilder {
        private UUID uuid;
        private String name;

        private PersonBuilder() {
        }

        public static PersonBuilder aPerson() {
            return new PersonBuilder();
        }

        public PersonBuilder uuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.setUuid(uuid);
            person.setName(name);
            return person;
        }
    }
}
