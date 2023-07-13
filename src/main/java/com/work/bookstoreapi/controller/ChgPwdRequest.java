package com.work.bookstoreapi.controller;

public class ChgPwdRequest {
    private Integer id;
    private String oldPassword;
    private String newPassword;

    public ChgPwdRequest(){

    }

    public ChgPwdRequest(Integer id, String oldPassword, String newPassword){
        this.setId(id);
        this.setOldPassword(oldPassword);
        this.setNewPassword(newPassword);
    }

//    public Integer getEmailOfUser() {
//        return emailOfUser;
//    }

//    public void setEmailOfUser(String emailOfUser) {
//        this.emailOfUser = emailOfUser;
//    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
