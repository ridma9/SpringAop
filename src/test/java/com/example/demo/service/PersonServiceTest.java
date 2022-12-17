package com.example.demo.service;

import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import com.example.demo.mapper.PersonMapper;
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


class PersonDtoServiceTest {

    @Mock
    private PersonRepo personRepo;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonMapper personMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        people= Arrays.asList(new PersonDto("1","John",25));
        personDto = new PersonDto("1","Max",25);

    }
    PersonDto personDto = null;
    List<PersonDto> people= null;
    @Test
    void getPeople() {
        List<Person> persons= Arrays.asList(new Person("1","Max",25));
        when(personRepo.findAll()).thenReturn(persons);
        when(personMapper.entityToDtoMapper(persons.get(0))).thenReturn(personDto);
        assertEquals(1,service.getPeople().size());
    }

    @Test
    void savePerson() {
        Person person = new Person("1","Max",25);
        when(personMapper.dtoToEntityMapper(personDto)).thenReturn(person);
        when(personRepo.save(person)).thenReturn(person);
        assertEquals(personDto,service.savePerson(personDto));
    }

    @Test
    void getPersonByIdWhenFound() {
        Person person = new Person("1","Max",25);
        PersonDto personDto = new PersonDto("1","Max",25);
        when(personRepo.findById(Mockito.anyString())).thenReturn(Optional.of(person));
        when(personMapper.entityToDtoMapper(person)).thenReturn(personDto);
        assertEquals(personDto,service.getPersonById("1"));
    }

    @Test
    void getPersonByIdWhenNotFound() {
        Person person = new Person();
        PersonDto personDto = new PersonDto();
        when(personRepo.findById(Mockito.anyString())).thenReturn(Optional.of(person));
        when(personMapper.entityToDtoMapper(person)).thenReturn(personDto);
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
        Person person = new Person();
        when(personRepo.findById(Mockito.anyString())).thenReturn(Optional.of(person));
        service.deletePerson("1");
        verify(personRepo,times(0)).deleteById("1");

    }




}