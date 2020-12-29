package com.josivaniomarinho.personapi.service;

import com.josivaniomarinho.personapi.dto.request.PersonDTO;
import com.josivaniomarinho.personapi.dto.response.MessageResponseDTO;
import com.josivaniomarinho.personapi.entity.Person;
import com.josivaniomarinho.personapi.exception.PersonNotFoudException;
import com.josivaniomarinho.personapi.mapper.PersonMapper;
import com.josivaniomarinho.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //Save Person
    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return createMessageResponse(savedPerson.getId());
    }

    //Listed people
    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    //Return person by ID
    public PersonDTO findById(Long id) throws PersonNotFoudException {
        Person person = verifyById(id);

        return personMapper.toDTO(person);
    }

    //Delete person by iD
    public void delete(Long id) throws PersonNotFoudException {
        verifyById(id);

        personRepository.deleteById(id);
    }

    //Updadet person by ID
    public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoudException {
        verifyById(id);

        Person personToUpdate = personMapper.toModel(personDTO);

        Person updatedPerson = personRepository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId());
    }

    //Return exception
    private Person verifyById(Long id) throws PersonNotFoudException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoudException(id));
    }

    //Return menssage
    private MessageResponseDTO createMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }
}
