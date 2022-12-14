package com.example.demo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    Person person = new Person("1","Max",25);

    @Test
    void getName() {
        assertEquals("Max",person.getName());
    }

    @Test
    void getAge() {
        assertEquals(25,person.getAge());
    }

    @Test
    void setName() {
        person.setName("Ben");
        assertEquals("Ben",person.getName());
    }

    @Test
    void setAge() {
        person.setAge(20);
        assertEquals(20,person.getAge());
    }

    @Test
    void getId() {
        assertEquals("1",person.getId());
    }

    @Test
    void setId() {
        person.setId("101");
        assertEquals("101",person.getId());
    }

    Person person1 =new Person();
}