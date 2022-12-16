package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repo.PersonRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class PersonServiceTest {

    @Mock
    private PersonRepo personRepo;

    @InjectMocks
    private PersonService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
         people= Arrays.asList(new Person("1","John",25),new Person("2","Cal",20));
         person= new Person("3","Max",25);

    }
    Person person = null;
    List<Person> people= null;
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

    @Test
    void deletePersonWhenAvailable() {

        Person person = new Person("1","Max",25);
        when(personRepo.findById(Mockito.anyString())).thenReturn(Optional.of(person));
        service.deletePerson("1");
        verify(personRepo,times(1)).deleteById("1");
    }

    @Test
    void deletePersonWhenNotAvailable() {
        Person person = new Person("1","Max",25);
        when(personRepo.findById(Mockito.anyString())).thenReturn(null);
        service.deletePerson("1");
        verify(personRepo,times(0)).deleteById("1");

    }




}