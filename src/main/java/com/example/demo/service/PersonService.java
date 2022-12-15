package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repo.PersonRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepo repo;

    public PersonService(PersonRepo repo) {
        this.repo = repo;
    }

    public List<Person> getPeople(){
        return repo.findAll();
    }

    public void savePerson(Person person){
        repo.save(person);
    }

    public boolean deletePerson(String id){

        Optional<Person> person = repo.findById(id);

        if (person.isPresent()) {
            repo.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    public Person getPersonById(String id){
        Optional<Person> person = repo.findById(id);

        if (person.isPresent()) {
            return person.get();
        }else {
            return null;
        }
    }


}
