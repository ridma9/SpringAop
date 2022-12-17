package com.example.demo.mapper;

import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class PersonMapper {

    public PersonDto entityToDtoMapper(Optional<Person> person){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(person, PersonDto.class);
    }

    public Person dtoToEntityMapper(PersonDto personDto){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(personDto,Person.class);
    }
}
