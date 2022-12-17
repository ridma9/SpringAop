package com.example.demo.mapper;

import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonMapperTest {

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    PersonMapper mapper;
    Person person;
    PersonDto personDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        person =new Person("1","Max",25);
        personDto =new PersonDto("1","Max",25);
    }

    @Test
    void entityToDtoMapper() {
        when(modelMapper.map(person, PersonDto.class)).thenReturn(personDto);
        assertEquals(personDto,mapper.entityToDtoMapper(person));
    }

    @Test
    void dtoToEntityMapper() {
        when(modelMapper.map(personDto, Person.class)).thenReturn(person);
        assertEquals(person,mapper.dtoToEntityMapper(personDto));
    }
}