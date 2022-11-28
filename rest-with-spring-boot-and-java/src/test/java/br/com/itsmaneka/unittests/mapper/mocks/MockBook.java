package br.com.itsmaneka.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.itsmaneka.data.vo.v1.BookVO;
import br.com.itsmaneka.model.Book;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookVO mockVO() {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> Books = new ArrayList<Book>();
        for (int i = 0; i < 10; i++) {
            Books.add(mockEntity(i));
        }
        return Books;
    }

    public List<BookVO> mockVOList() {
        List<BookVO> Books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Books.add(mockVO(i));
        }
        return Books;
    }
    
    public Book mockEntity(Integer number) {
        Book Book = new Book();
        Book.setAuthor("Author Test " + number);
        Book.setLaunchDate(new Date());
        Book.setPrice(Double.valueOf(number));
        Book.setId(number.longValue());
        Book.setTitle("Book Test " + number);
        return Book;
    }

    public BookVO mockVO(Integer number) {
        BookVO Book = new BookVO();
        Book.setAuthor("Author Test " + number);
        Book.setLaunchDate(new Date());
        Book.setPrice(Double.valueOf(number));
        Book.setKey(number.longValue());
        Book.setTitle("Book Test " + number);
        return Book;
    }

}
