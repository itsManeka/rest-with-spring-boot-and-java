package br.com.itsmaneka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itsmaneka.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
