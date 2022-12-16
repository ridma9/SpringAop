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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
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
    private PersonService service;

    List<Person> people = Arrays.asList(new Person("1","John",25),new Person("2","Cal",20));

    @Test
    void getAll() throws Exception {
        Mockito.when(service.getPeople()).thenReturn(people);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/people").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "[{\"id\":\"1\",\"name\":\"John\",\"age\":25},{\"id\":\"2\",\"name\":\"Cal\",\"age\":20}]";
        assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    void getOnePerson() throws Exception {
        Person person = people.get(0);
        Mockito.when(service.getPersonById(Mockito.anyString())).thenReturn(person);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/people/\"1\"").accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{\"id\":\"1\",\"name\":\"John\",\"age\":25}";
        assertEquals(expected,result.getResponse().getContentAsString());
    }

    @Test
    void addPerson() throws Exception {
        Person person = new Person("4","Max",30);
        String mockPerson = "{\"id\":\"4\",\"name\":\"Max\",\"age\":30}";
        Mockito.when(service.savePerson(Mockito.any(Person.class))).thenReturn(person);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/people")
                .accept(MediaType.APPLICATION_JSON)
                .content(mockPerson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(mockPerson, result.getResponse().getContentAsString());
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

    }

    @Test
    void deletePerson() throws Exception {
        Mockito.when(service.deletePerson(Mockito.anyString())).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/people/1");
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }

    @Test
    void updatePerson() throws Exception {
        Person person = new Person("1","Max",35);
        String mockPerson = "{\"id\":\"1\",\"name\":\"Max\",\"age\":30}";
        Mockito.when(service.getPersonById(Mockito.any())).thenReturn(person);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/people/\"1\"")
                .accept(MediaType.APPLICATION_JSON)
                .content(mockPerson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(mockPerson, result.getResponse().getContentAsString());
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());

    }


}