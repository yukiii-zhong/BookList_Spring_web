package com.yuki.springbootdemo.web;

import com.yuki.springbootdemo.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class HelloController {

    @RequestMapping("/say")
    public String hello(){
        return "Hello World";
    }


    @GetMapping("/books")
    // URL: http://127.0.0.1:8080/api/v1/books?page=1&size=10
    public Object getBookList(@RequestParam("page") int page,
                              @RequestParam(value = "size", defaultValue = "10") int size){

        Map<String, Object> b1 = new HashMap<>();
        b1.put("name","世界观");
        b1.put("isbn", "12335421");
        b1.put("author", "James");

        Map<String, Object> b2 = new HashMap<>();
        b2.put("name","我的世界");
        b2.put("isbn", "08971239879");
        b2.put("author", "Yuki");

        List<Map> bookList = new ArrayList<>();
        bookList.add(b1);
        bookList.add(b2);

        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("size", size);
        pageMap.put("contents", bookList);

        return pageMap;
    }

    //URL: .../api/v1/books?name=abc&author=ABC&isbn=1234567
    @PostMapping("/books")
    public Object post(@RequestParam("name") String name,
                       @RequestParam String author,
                       @RequestParam String isbn){
        Map<String, Object> book = new HashMap<String, Object>();
        book.put("name", name);
        book.put("author", author);
        book.put("isbn", isbn);
        return book;
    }

    @GetMapping("/books/{id}")
    public Object getOne(@PathVariable long id){

        return null;
    }

}
