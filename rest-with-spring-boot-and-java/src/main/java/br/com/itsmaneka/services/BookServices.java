package br.com.itsmaneka.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itsmaneka.controllers.BookController;
import br.com.itsmaneka.data.vo.v1.BookVO;
import br.com.itsmaneka.exceptions.RequiredObjectIsNullException;
import br.com.itsmaneka.exceptions.ResourceNotFoundException;
import br.com.itsmaneka.mapper.DozerMapper;
import br.com.itsmaneka.model.Book;
import br.com.itsmaneka.repositories.BookRepository;

@Service
public class BookServices {
    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    public List<BookVO> findAll() {
        logger.info("Finding all!");
        
        var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
        books
            .stream()
            .forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));

        return books;
    }

    public BookVO findById(Long id) {
        logger.info("Finding one BookVO!");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem dados para esse ID"));
        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
        return vo;
    }

    public BookVO create(BookVO books) {
        if (books == null) throw new RequiredObjectIsNullException();
        logger.info("Creating one BookVO!");
        var entity = DozerMapper.parseObject(books, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public BookVO update(BookVO books) {
        if (books == null) throw new RequiredObjectIsNullException();
        logger.info("Updating one BooksO!");
        
        var entity = repository.findById(books.getKey()).orElseThrow(() -> new ResourceNotFoundException("Sem dados para esse ID"));

        entity.setAuthor(books.getAuthor());
        entity.setLaunchDate(books.getLaunchDate());
        entity.setPrice(books.getPrice());
        entity.setTitle(books.getTitle());

        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
        return vo;
    }

    public void delete(Long id) {
        logger.info("deleting one BookVO!");

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sem dados para esse ID"));
        repository.delete(entity);
    }
}
