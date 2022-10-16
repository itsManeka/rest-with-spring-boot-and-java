package br.com.itsmaneka.restwithspringbootandjava.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itsmaneka.restwithspringbootandjava.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
