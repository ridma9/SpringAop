package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repo.PersonRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepo personRepo;

    @InjectMocks
    private PersonService service;

    List<Person> people = Arrays.asList(new Person("1","John",25),new Person("2","Cal",20));
    Person person = new Person("3","Max",25);

    @Test
    void getPeople() {
        when(personRepo.findAll()).thenReturn(people);
        assertEquals(2,service.getPeople().size());
    }

    @Test
    void savePerson() {
        when(personRepo.save(person)).thenReturn(person);
        assertEquals(person,service.savePerson(person));
    }

    @Test
    void deletePersonWhenPresent() {
/*
        Person person = new Person("1","Max",25);
        service.deletePerson("1");
        assertEquals(false,personRepo.findById("1").isPresent());
        verify(personRepo).deleteById(any());
*/

        service.deletePerson("1");
        verify(personRepo, times(1)).deleteById(anyString());

    }


    @Test
    void getPersonByIdWhenFound() {
        Person person = new Person("1","Max",25);
        when(personRepo.findById(Mockito.anyString())).thenReturn(Optional.of(person));
        assertEquals(person,service.getPersonById("1"));
    }

    @Test
    void getPersonByIdWhenNotFound() {
        Person person = new Person("1","Max",25);
        when(personRepo.findById(Mockito.anyString())).thenReturn(Optional.of(new Person()));
        assertEquals(null,service.getPersonById("1").getId());
    }

}