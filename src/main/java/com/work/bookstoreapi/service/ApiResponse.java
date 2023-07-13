package com.work.bookstoreapi.service;

public class ApiResponse {

    private String message;
    private String responseCode;
    private Object data;

    public ApiResponse(){
    }

    public ApiResponse(String responseCode, String message, Object object){
        this.responseCode = responseCode;
        this.message = message;
        this.data = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

