package br.com.itsmaneka.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itsmaneka.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {}
