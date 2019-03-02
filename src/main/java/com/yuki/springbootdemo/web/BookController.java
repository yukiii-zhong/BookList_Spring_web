package com.yuki.springbootdemo.web;

import com.yuki.springbootdemo.domain.Book;
import com.yuki.springbootdemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("v3")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public String list(Model model){
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String detail(@PathVariable long id, Model model){
        Book book = bookService.getOne(id);
        if (book == null){
            book = new Book();
        }
        model.addAttribute("book", book);
        return "book";
    }

    /**
     * 跳转input提交页面
     * @return
     */
    @GetMapping("/books/input")
    public String inputPage(Model model){
        model.addAttribute("book", new Book());
        return "input";
    }

    @GetMapping("/books/input/{id}")
    public String inputEditPage(@PathVariable long id, Model model){
        Book book=bookService.getOne(id);
        model.addAttribute("book", book);
        return "input";
    }

    /**
     * 提交一个书单信息
     */
    @PostMapping("/books")
    public String post(Book book){
        bookService.save(book);
        return "redirect:/v3/books";
    }
}
