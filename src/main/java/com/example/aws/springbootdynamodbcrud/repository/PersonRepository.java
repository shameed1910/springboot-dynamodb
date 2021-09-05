package com.example.aws.springbootdynamodbcrud.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.aws.springbootdynamodbcrud.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepository  {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Person save(Person person){
        dynamoDBMapper.save(person);
        return person;
    }

    public Person findById(String id){
       return dynamoDBMapper.load(Person.class, id);
    }

    public List<Person> findAll(){
        return dynamoDBMapper.scan(Person.class, new DynamoDBScanExpression());
    }

    public String update(String id, Person person){
        dynamoDBMapper.save(person,
                new DynamoDBSaveExpression()
        .withExpectedEntry("id",
                new ExpectedAttributeValue(
                        new AttributeValue().withS(id)
                )));
        return id;
    }

    public String delete(String id){
       Person person = dynamoDBMapper.load(Person.class, id);
        dynamoDBMapper.delete(person);
        return "Person deleted successfully:: "+id;
    }


}
