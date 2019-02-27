package com.yuki.springbootdemo.web;


import com.yuki.springbootdemo.domain.Book;
import com.yuki.springbootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("v2")
public class BookApp{

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAll(){
        return bookService.findAll();
    }

    @PostMapping("/books")
    public Book addBook(@RequestParam String name,
                        @RequestParam String author,
                        @RequestParam String description,
                        @RequestParam int status){
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);
        return bookService.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable long id){
        return null;
    }

    @PutMapping("/books")
    public Book update(@RequestParam long id,
                       @RequestParam String name,
                       @RequestParam String author,
                       @RequestParam String description,
                       @RequestParam int status){
        Book book = new Book();
        book.setId(id);
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setStatus(status);

        return bookService.save(book);
    }

    @DeleteMapping("/books/{id}")
    public String delete(@PathVariable long id){
//        bookService.delete(id);
        return bookService.deleteByJPQL(id);
    }

    @PostMapping("/books/by")
    public String findBy(@RequestParam int status,
                             @RequestParam long id){
//        return bookService.findByDescriptionContains(description);
//        return bookService.findByJPQL(len);
        return bookService.updateByJPQL(status,id);
    }
}
