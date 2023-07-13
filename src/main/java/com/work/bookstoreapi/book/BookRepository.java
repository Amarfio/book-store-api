package com.work.bookstoreapi.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Integer> {
Optional<Book> findByTitle(String title);
}
