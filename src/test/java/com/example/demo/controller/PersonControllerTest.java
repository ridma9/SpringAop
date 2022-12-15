package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.repo.PersonRepo;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = PersonController.class)
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private  PersonRepo repo;

    @MockBean
    private PersonService service;

    List<Person> people = Arrays.asList(new Person("1","John",25));

    @Test
    void getAll() throws Exception {
        Mockito.when(service.getPeople()).thenReturn(people);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/get").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":\"1\",\"name\":\"John\",\"age\":25}]";
        assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    void addPerson() {

    }

    @Test
    void deletePerson() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void getOnePerson() {
    }
}