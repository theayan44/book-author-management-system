package com.example.mockspring2.repository;

import com.example.mockspring2.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    public Author findByEmail(String email);
}
