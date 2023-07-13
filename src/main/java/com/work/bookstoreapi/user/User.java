package com.work.bookstoreapi.user;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name="users")
public class User implements UserDetails {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name="name")
    private String name;

    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @Column(name="fileName")
    private String fileName;

    @Column(name="fileType")
    private String fileType;

    @Column(name="filePath")
    private String filePath;

    @Column(name="updatedAt")
    private LocalDateTime updatedAt;

    //default contructor
    public User(){
        //create a default user
        this("joshuaamarfio1@outlook.com", "kyc32$91", "Josh");
        this.setCreatedAt(LocalDateTime.now());
    }

    //contructor specifying user email and password...
    public User(String email, String password, String name) {
        this.setEmail(email);
        this.setPassword(password);
        this.setName(name);
    }

    @Column(nullable = false, length = 100)
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

