package com.example.demo.controller;

import com.example.demo.dto.PersonDto;
import com.example.demo.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PersonController {

    private final PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public List<PersonDto> getAll(){
        return service.getPeople();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> getOnePerson(@PathVariable String id){
        return ResponseEntity.ok(service.getPersonById(id));
    }

    @PostMapping()
    public ResponseEntity<PersonDto> addPerson(@RequestBody PersonDto personDto){
        service.savePerson(personDto);
        return ResponseEntity.ok(personDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable String id){
        if (service.deletePerson(id)){
            return ResponseEntity.ok("Deleted");
        }else {
            return ResponseEntity.ok("PersonDto Not Found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable String id, @RequestBody PersonDto personDto){
        PersonDto personDto1 = service.getPersonById(id);

        if (personDto1 ==null){
            return ResponseEntity.ok("Not Updated, PersonDto Not Found");
        }else {
            personDto1.setAge(personDto.getAge());
            personDto1.setName(personDto.getName());
            service.savePerson(personDto1);
            return ResponseEntity.ok("PersonDto Updated");
        }

    }



}
