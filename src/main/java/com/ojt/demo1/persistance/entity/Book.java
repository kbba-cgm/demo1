package com.ojt.demo1.persistance.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String body;
    @ManyToOne
    @JoinColumn(name = "author_id")
    Author author;
}
