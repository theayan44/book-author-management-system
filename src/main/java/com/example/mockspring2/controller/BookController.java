package com.example.mockspring2.controller;

import com.example.mockspring2.model.Author;
import com.example.mockspring2.model.Book;
import com.example.mockspring2.model.Publish;
import com.example.mockspring2.service.BookService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping("/add")
    public ResponseEntity addBook(@RequestParam("bookName") String bookName, @RequestParam("pages") int pages, @RequestParam("authorName") String authorEmail){
        try {
            Book response = bookService.addBook(bookName, pages, authorEmail);
            return new ResponseEntity<>(response+" ", HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/publish-book")
    public ResponseEntity publishBook(@RequestParam("bookId") int bookId, @RequestParam("authorEmail") String authorEmail, @RequestParam("year") String year){
        try {
            Publish response = bookService.publishBook(bookId, authorEmail, year);
            return  new ResponseEntity<>(response+" ", HttpStatus.CREATED);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/find-book")
    public ResponseEntity findBook(@RequestParam("year") String year, @RequestParam("authorEmail") String authorEmail){
        try {
            int noOfBooks = bookService.findBook(year, authorEmail);
            return new ResponseEntity<>(noOfBooks, HttpStatus.FOUND);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/book-by-female-author")
    public ResponseEntity bookByFemaleAuthor(@RequestParam("fromYear") String fromYear, @RequestParam("toYear") String toYear){
        try {
            int noOfBooks = bookService.bookByFemaleAuthor(fromYear, toYear);
            return new ResponseEntity<>(noOfBooks, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
