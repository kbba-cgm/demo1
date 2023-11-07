package com.ojt.demo1.bl.service;

import com.ojt.demo1.persistance.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
