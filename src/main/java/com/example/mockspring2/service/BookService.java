package com.example.mockspring2.service;

import com.example.mockspring2.Enums.Gender;
import com.example.mockspring2.model.Author;
import com.example.mockspring2.model.Book;
import com.example.mockspring2.model.Publish;
import com.example.mockspring2.repository.AuthorRepository;
import com.example.mockspring2.repository.BookRepository;
import com.example.mockspring2.repository.PublishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    PublishRepository publishRepository;



    public Book addBook(String bookName, int pages, String authorEmail) {
        Author author = authorRepository.findByEmail(authorEmail);
        if(author == null){
            throw new RuntimeException("Author Doesn't Exists");
        }

        Book book = new Book();
        book.setBookName(bookName);
        book.setPages(pages);
        book.setAuthor(author);

        Book savedBook = bookRepository.save(book);

        //update in Author
        author.getBookList().add(savedBook);
        authorRepository.save(author);

        return savedBook;
    }

    public Publish publishBook(int bookId, String authorEmail, String publishingYear) {
        Author author = authorRepository.findByEmail(authorEmail);
        if(author == null){
            throw new RuntimeException("Author Doesn't Exists");
        }

        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if(optionalBook.isEmpty()){
            throw new RuntimeException("Book doesn't exists");
        }

        Publish publish = new Publish();
        publish.setBook(optionalBook.get());
        publish.setAuthor(author);
        publish.setPublishingYear(publishingYear);

        return publishRepository.save(publish);
    }

    public int findBook(String year, String authorEmail) {
        Author author = authorRepository.findByEmail(authorEmail);
        if(author == null){
            throw new RuntimeException("Author Doesn't Exists");
        }

        List<Publish> publishes = publishRepository.findByYear(year);
        int count = 0;
        for(Publish currPublish : publishes){
            if(currPublish.getAuthor() == author)
                count++;
        }
        return count;
    }

    public int bookByFemaleAuthor(String fromYear, String toYear) {
        List<Publish> publishes = publishRepository.findBookInRange(fromYear, toYear);
        int count = 0;

        for(Publish currPublish : publishes){
            if(currPublish.getAuthor().getGender() == Gender.FEMALE)
                count++;
        }

        return count;
    }
}
