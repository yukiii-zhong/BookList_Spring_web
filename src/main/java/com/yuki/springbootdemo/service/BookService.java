package com.yuki.springbootdemo.service;

import com.yuki.springbootdemo.domain.Book;
import com.yuki.springbootdemo.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepositry;

    //JpaRepository 自带的function，不用新建
    //获取所有清单
    public List<Book> findAll(){
        return bookRepositry.findAll();
    }


//    public Page<Book> findAllbyPage(){
//
//        Pageable pageable = new Pageable();
//        return bookRepositry.findAll();
//    }

    // 新增一个书单
    public Book save(Book book){
        return bookRepositry.save(book);
    }
    //获取一条清单
    public Book getOne(long id){
        return bookRepositry.getOne(id);
    }
    //删除一个清单
    public void delete(long id){
        bookRepositry.deleteById(id);
    }

    //在BookRepository 里新建后使用
    public List<Book> findByAuthor(String author){
        return bookRepositry.findByAuthor(author);
    }
    public List<Book> findByAuthorAndStatus(String author, int status){
        return bookRepositry.findByAuthorAndStatus(author,status);
    }
    public List<Book> findByDescriptionContains(String des){
        return bookRepositry.findByDescriptionContains(des);
    }

    //在BookRepository 里用@Query定义后使用
    //自定义查询
    public List<Book> findByJPQL(int len){
        return bookRepositry.findByJPQL(len);
    }
    //自定义更新
    public String updateByJPQL(int status, long id){
        int updateStatus =  bookRepositry.updateByJPQL(status, id);
        if (updateStatus == 1){
            return "更新成功";
        }
        else return "更新失败";
    }
    public String deleteByJPQL(long id){
        int deleteStatus =  bookRepositry.deleteByJPQL(id);
        if (deleteStatus == 1){
            return "删除成功";
        }
        else return "删除失败";
    }
}
