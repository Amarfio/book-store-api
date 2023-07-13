package com.work.bookstoreapi.book;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //the name of the book
    @Column(name = "title")
    private String title;

//    @Lob
    //writer of the book
    @Column(name= "author", length = 50)
    private String author;

    @Column(name= "genre", length = 50)
    private String genre;

    @Column(name= "isActive", length=2)
    private Boolean isActive;

    @Column(name="postedBy")
    private Integer postedBy;

    @Column(name="postedDate")
    private LocalDateTime postedDate;

    @Column(name="updatedDate")
    private LocalDateTime updatedDate;

    public Book(){

    }

    public Book(String title, String author, String genre){
        this.setTitle(title);
        this.setAuthor(author);
        this.setGenre(genre);
    }
//    public Book(String title, String author, String genre){
//        this.set
//    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
    }

    public LocalDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDateTime postedDate) {
        this.postedDate = postedDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
