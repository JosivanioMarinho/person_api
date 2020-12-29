package com.josivaniomarinho.personapi.repository;

import com.josivaniomarinho.personapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
