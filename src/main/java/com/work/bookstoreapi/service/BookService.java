package com.work.bookstoreapi.service;


import com.work.bookstoreapi.book.Book;
import com.work.bookstoreapi.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;

public class BookService {

    @Autowired
    BookRepository bookRepository;

    String responseCode = "000";
    String message = "default message";
    Object responseObject =null;

    ApiResponse apiResponse;

    public ResponseEntity<ApiResponse> addNewBook(Book book){
        Book newBook = null;

//        filePath = FOLDER_PATH+file.getOriginalFilename();

        try{
            Optional<Book> checkBook = bookRepository.findByTitle(book.getTitle());

            if(!checkBook.isEmpty()){
                responseCode = "417";
                message = "blog title already exists";
                apiResponse = new ApiResponse(responseCode, message, responseObject);
            }else{
                newBook = book;
                newBook.setPostedDate(LocalDateTime.now());
                newBook.setIsActive(false);
//                newBook.setApproved(false);
//                newBlog.setFilePath(filePath);


                //save a copy of the file that was uploaded
//                file.transferTo(new File(filePath));
                bookRepository.save(newBook);
                apiResponse = new ApiResponse("200", "blog created and pending approval", newBook);



            }
            return ResponseEntity.ok(apiResponse);
        }catch(Exception ex){
            return ResponseEntity.ok(new ApiResponse("400", ex.getMessage(), null));
        }
    }
}
