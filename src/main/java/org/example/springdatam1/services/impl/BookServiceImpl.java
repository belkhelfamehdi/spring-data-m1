package org.example.springdatam1.services.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Temporal;
import jakarta.transaction.Transactional;
import org.example.springdatam1.entity.Book;
import org.example.springdatam1.repository.BookRepository;
import org.example.springdatam1.services.BookService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book save(Book entity) {
        return bookRepository.save(entity);
    }

    @Override
    @Transactional
    public Optional<Book> findById(Long id) {
        Book book = bookRepository
                .findById(id)
                .orElseThrow(
                        ()->new EntityNotFoundException("Book not found"));
        Hibernate.initialize(book.getGenres());
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book update(Long aLong, Book entity) {
        return null;
    }

    @Override
    public void delete(Long aLong) {
        bookRepository.findById(aLong).orElseThrow(()->new EntityNotFoundException("Book not found"));
        bookRepository.deleteById(aLong);
    }
}
