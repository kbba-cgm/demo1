package com.ojt.demo1.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MyUser {
    @Id
    @GeneratedValue
    Long id;
    String username;
    Integer phoneNumber;
    Integer gender;
    Boolean married;
    String city;
}
