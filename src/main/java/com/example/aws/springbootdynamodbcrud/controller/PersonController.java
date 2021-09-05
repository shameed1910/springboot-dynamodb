package com.example.aws.springbootdynamodbcrud.controller;


import com.example.aws.springbootdynamodbcrud.entity.Person;
import com.example.aws.springbootdynamodbcrud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @PostMapping
    public Person save(@RequestBody Person person){
        return personRepository.save(person);
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable(value = "id") String id){
        return personRepository.findById(id);
    }

    @GetMapping
    public List<Person> findAll(){
        return personRepository.findAll();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable(value = "id") String id,
    @RequestBody Person person){
        return personRepository.update(id, person);
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") String id){
        return personRepository.delete(id);
    }
}
