package com.example.demo.service;

import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.repo.PersonRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepo repo;
    private final PersonMapper personMapper;
    public PersonService(PersonRepo repo, PersonMapper personMapper) {
        this.repo = repo;
        this.personMapper = personMapper;
    }

    public List<PersonDto> getPeople(){
        List<PersonDto> people = new ArrayList<>();
        for (Person person:repo.findAll()) {
            people.add(personMapper.entityToDtoMapper(Optional.ofNullable(person)));
        }
        return people;
    }

    public PersonDto savePerson(PersonDto personDto){
        Person person = personMapper.dtoToEntityMapper(personDto);
        repo.save(person);
        return personDto;
    }

    public boolean deletePerson(String id){
        Optional<Person> person = repo.findById(id);
        if (person.isPresent() && person.get().getId()!=null) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public PersonDto getPersonById(String id){
        Optional<Person> person = repo.findById(id);
        Optional<PersonDto> personDto = Optional.ofNullable(personMapper.entityToDtoMapper(person));
        return personDto.orElse(null);
    }


}
