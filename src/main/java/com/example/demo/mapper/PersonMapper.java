package com.example.demo.mapper;

import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class PersonMapper {


    private final ModelMapper mapper;

    public PersonMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }


    public PersonDto entityToDtoMapper(Person person){
        return mapper.map(person, PersonDto.class);
    }

    public Person dtoToEntityMapper(PersonDto personDto){
        return mapper.map(personDto,Person.class);
    }
}
