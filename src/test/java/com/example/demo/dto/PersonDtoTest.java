package com.example.demo.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDtoTest {
    PersonDto personDto = new PersonDto("1","Max",25);

    @Test
    void getName() {
        assertEquals("Max", personDto.getName());
    }

    @Test
    void getAge() {
        assertEquals(25, personDto.getAge());
    }

    @Test
    void setName() {
        personDto.setName("Ben");
        assertEquals("Ben", personDto.getName());
    }

    @Test
    void setAge() {
        personDto.setAge(20);
        assertEquals(20, personDto.getAge());
    }

    @Test
    void getId() {
        assertEquals("1", personDto.getId());
    }

    @Test
    void setId() {
        personDto.setId("101");
        assertEquals("101", personDto.getId());
    }

    PersonDto personDto1 =new PersonDto();
}