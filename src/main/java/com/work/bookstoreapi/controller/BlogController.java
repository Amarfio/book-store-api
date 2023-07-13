//package com.work.bookstoreapi.controller;
//
//import com.work.bookstoreapi.blog.Blog;
//import com.work.bookstoreapi.service.ApiResponse;
//import com.work.bookstoreapi.service.BlogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/blog")
//@CrossOrigin("*")
//public class BlogController {
//
//    @Autowired
//    BlogService blogService;
//
//    //endpoint to create a new blog using default values
//    @PostMapping("/create-default")
//    public ResponseEntity<?> create(){
//
//        //catch any error that comes up in the execution of the endpoint....
//        try{
//            Blog blog = new Blog();
//            return blogService.defaultBlog(blog);
//        }catch(Exception ex){
//            return ResponseEntity.ok(new ApiResponse("400", ex.getMessage(), null));
//        }
//    }
//
//
//
//    //endpoint to create new blog with user input
//    @PostMapping("/add-new")
//    public ResponseEntity<?> create(@RequestPart("body") Blog blog, @RequestPart("image")MultipartFile file){
//
//        //catch any error that comes up in the creation of a new blog using user input
//        try{
//            return blogService.addNewBlog(blog, file);
//        }catch(Exception ex){
//            return ResponseEntity.ok(new ApiResponse("400", ex.getMessage(), null));
//        }
//    }
//
//    //endpoint to update blog using id
//    @PutMapping("/update")
//    public ResponseEntity<?> updateBlogById(@RequestBody Blog blog){
//        //catch the error if it comes up.
//        try{
//            return blogService.updateBlogById(blog);
//        }catch (Exception ex){
//            return ResponseEntity.ok(new ApiResponse("400", ex.getMessage(), null));
//        }
//    }
//
//    //endpoint to get blog details using id
//    @GetMapping("/get-by-id")
//    public ResponseEntity<?> getBlogById(@RequestParam Integer id){
//        //catch the error if it comes up
//        try{
//            return blogService.getBlogPostById(id);
//        }catch (Exception ex){
//            return ResponseEntity.ok(new ApiResponse("400", ex.getMessage(), null));
//        }
//    }
//
//    //endpoint to get all blogs posted
//    @GetMapping("/get-all")
//    public ResponseEntity<?> getAllBlogs(){
//        //catch the error if it comes up
//        try{
//            return blogService.listBlogs();
//        } catch (Exception ex){
//            return ResponseEntity.ok(new ApiResponse("400", ex.getMessage(), null));
//        }
//    }
//
//    //endpoint to activate blog
//    @GetMapping("/activate")
//    public ResponseEntity<?> activateBlog(@RequestParam Integer id){
//        //catch the error if it comes up
//        try{
//            return blogService.activateBlog(id);
//        } catch (Exception ex){
//            return ResponseEntity.ok(new ApiResponse("400", ex.getMessage(), null));
//        }
//    }
//}
