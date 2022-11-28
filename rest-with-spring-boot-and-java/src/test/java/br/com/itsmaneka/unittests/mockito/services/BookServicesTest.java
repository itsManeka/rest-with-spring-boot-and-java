package br.com.itsmaneka.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.itsmaneka.data.vo.v1.BookVO;
import br.com.itsmaneka.exceptions.RequiredObjectIsNullException;
import br.com.itsmaneka.model.Book;
import br.com.itsmaneka.repositories.BookRepository;
import br.com.itsmaneka.services.BookServices;
import br.com.itsmaneka.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServicesTest {

    MockBook input;

    @InjectMocks
    private BookServices service;

    @Mock
    BookRepository repository;

    @BeforeEach
    void setUpMocks() throws Exception {
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Book entity = input.mockEntity(1);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.save(entity)).thenReturn(persisted);

        var result = service.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test 1", result.getAuthor());
        assertEquals(1.00D, result.getPrice());
        assertEquals("Book Test 1", result.getTitle());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void testCreateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.create(null);
        });

        String expectedMessage = "Não é permitido persistir um objeto nulo!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testDelete() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        
        service.delete(1L);

    }

    @Test
    void testFindAll() {
        List<Book> list = input.mockEntityList();

        when(repository.findAll()).thenReturn(list);
        
        var people = service.findAll();
        assertNotNull(people);
        assertEquals(10, people.size());

        var BookOne = people.get(1);
        
        assertNotNull(BookOne);
        assertNotNull(BookOne.getKey());
        assertNotNull(BookOne.getLinks());

        assertTrue(BookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test 1", BookOne.getAuthor());
        assertEquals(1.00D, BookOne.getPrice());
        assertEquals("Book Test 1", BookOne.getTitle());
        assertNotNull(BookOne.getLaunchDate());

        var BookFour = people.get(4);
        
        assertNotNull(BookFour);
        assertNotNull(BookFour.getKey());
        assertNotNull(BookFour.getLinks());

        assertTrue(BookFour.toString().contains("links: [</api/book/v1/4>;rel=\"self\"]"));
        assertEquals("Author Test 4", BookFour.getAuthor());
        assertEquals(4.00D, BookFour.getPrice());
        assertEquals("Book Test 4", BookFour.getTitle());
        assertNotNull(BookFour.getLaunchDate());

        var BookSeven = people.get(7);
        
        assertNotNull(BookSeven);
        assertNotNull(BookSeven.getKey());
        assertNotNull(BookSeven.getLinks());

        assertTrue(BookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
        assertEquals("Author Test 7", BookSeven.getAuthor());
        assertEquals(7.00D, BookSeven.getPrice());
        assertEquals("Book Test 7", BookSeven.getTitle());
        assertNotNull(BookSeven.getLaunchDate());
    }

    @Test
    void testFindById() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        
        var result = service.findById(1L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test 1", result.getAuthor());
        assertEquals(1.00D, result.getPrice());
        assertEquals("Book Test 1", result.getTitle());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void testUpdate() {
        Book entity = input.mockEntity(1);
        entity.setId(1L);

        Book persisted = entity;
        persisted.setId(1L);

        BookVO vo = input.mockVO(1);
        vo.setKey(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(repository.save(entity)).thenReturn(persisted);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
        assertEquals("Author Test 1", result.getAuthor());
        assertEquals(1.00D, result.getPrice());
        assertEquals("Book Test 1", result.getTitle());
        assertNotNull(result.getLaunchDate());
    }

    @Test
    void testUpdateWithNullBook() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
            service.update(null);
        });

        String expectedMessage = "Não é permitido persistir um objeto nulo!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
