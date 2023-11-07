package com.ojt.demo1.bl.service;

import com.ojt.demo1.persistance.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
