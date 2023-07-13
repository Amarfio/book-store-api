package com.work.bookstoreapi.controller;

import com.work.bookstoreapi.book.Book;
import com.work.bookstoreapi.service.ApiResponse;
import com.work.bookstoreapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/book")
@CrossOrigin("*")
public class BookController {

    @Autowired
    BookService bookService;

    String responseCode = "000";
    String message = "default message";
    Object responseObject =null;

    ApiResponse apiResponse;

//    public ResponseEntity<ApiResponse> addNewBlog(Book book){
//        Book newBook = null;
//
//
//        try{
//            Optional<Book> checkBook = bookR.findByTitle(book.getTitle());
//
//            if(!checkBlog.isEmpty()){
//                responseCode = "409";
//                message = "blog title already exists";
//                apiResponse = new ApiResponse(responseCode, message, responseObject);
//            }else{
//                newBlog = blog;
//                newBlog.setPostedDate(LocalDateTime.now());
//                newBlog.setActive(false);
//                newBlog.setApproved(false);
//                newBlog.setFilePath(filePath);
//
//
//                //save a copy of the file that was uploaded
//                file.transferTo(new File(filePath));
//                blogRepo.save(newBlog);
//                apiResponse = new ApiResponse("200", "blog created and pending approval", newBlog);
//
//
//
//            }
//            return ResponseEntity.ok(apiResponse);
//        }catch(Exception ex){
//            return ResponseEntity.ok(new ApiResponse("400", ex.getMessage(), null));
//        }
//    }


}
