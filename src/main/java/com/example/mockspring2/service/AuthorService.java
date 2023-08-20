package com.example.mockspring2.service;

import com.example.mockspring2.model.Author;
import com.example.mockspring2.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Author addAuthor(Author author) {
        author.setBookList(new ArrayList<>());
        return authorRepository.save(author);
    }
}
