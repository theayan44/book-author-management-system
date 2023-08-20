package com.example.mockspring2.model;

import com.example.mockspring2.Enums.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(unique = true)
    String email;

    int age;

    @Enumerated(EnumType.STRING)
    Gender gender;

    double rating;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    List<Book> bookList;
}
