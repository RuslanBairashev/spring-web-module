package ru.edu.springweb.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.edu.springweb.entity.Book;
import ru.edu.springweb.exception.BookAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    public List<Book> getAllBooks() {
        return books;
    }

    public Optional<Book> getBookById(int bookId) {
        return books.stream().filter(book -> book.getId() == bookId).findFirst();
    }

    public boolean saveBook(Book book) {
        books.stream().filter(b -> b.equals(book)).findAny().ifPresent(b -> {throw new BookAlreadyExistsException(  "User already exists");});
        return books.add(book);
    }

    public void updateBook(Book book) {
        Optional<Book> bookForUpdate = getBookById(book.getId());
        bookForUpdate.ifPresent(
                b -> {
                    b.setTitle(book.getTitle());
                    b.setAuthor(book.getAuthor());
                }
        );
    }

    public void deleteBook(int bookId) {
        books.removeIf(book -> book.getId() == bookId);
    }

    @PostConstruct
    public void init() {
        books.add(new Book(1, "User1", "Aaa"));
        books.add(new Book(2, "User2", "Bbb"));
        books.add(new Book(3, "User3", "Ccc"));
        books.add(new Book(4, "User4", "Ddd"));
    }
}
