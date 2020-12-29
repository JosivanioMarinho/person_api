package com.josivaniomarinho.personapi.service;

import com.josivaniomarinho.personapi.dto.response.MessageResponseDTO;
import com.josivaniomarinho.personapi.entity.Person;
import com.josivaniomarinho.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return com.josivaniomarinho.personapi.dto.response.MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}
