package com.example.demo.controller;

import com.example.demo.entity.Person;
import com.example.demo.repo.PersonRepo;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping("/get")
    public List<Person> getAll(){
        return service.getPeople();
    }

    @PostMapping(value = "/add")
    public ResponseEntity addPerson(@RequestBody Person person){
        service.savePerson(person);
        return ResponseEntity.ok("Person Added");
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deletePerson(@PathVariable String id){
        if (service.deletePerson(id)){
            return ResponseEntity.ok("Deleted");
        }else {
            return ResponseEntity.ok("Person Not Found");
        }
    }

    @PutMapping("update/{id}")
    public ResponseEntity updatePerson(@PathVariable String id, @RequestBody Person person){
        Person person1 = service.getPersonById(id);

        if (person1==null){
            //return new ResponseEntity(HttpStatus.NOT_MODIFIED);
            return ResponseEntity.ok("Not Updated, Person Not Found");
        }else {
            person1.setAge(person.getAge());
            person1.setName(person.getName());
            service.savePerson(person1);
            return ResponseEntity.ok("Person Updated");
        }

    }

    @GetMapping("get/{id}")
    public ResponseEntity getOnePerson(@PathVariable String id){

        if (service.getPersonById(id)==null){
            //return new ResponseEntity(HttpStatus.NOT_FOUND);
            return ResponseEntity.ok("Person Not Found");
        }else {
            return ResponseEntity.ok(service.getPersonById(id));
        }
    }

}
