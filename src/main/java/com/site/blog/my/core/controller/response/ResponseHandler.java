package com.site.blog.my.core.controller.response;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ResponseHandler {

    private HttpStatus status;
    private List data;
    private Object bean;
    private String message;
    private String token;

    public ResponseHandler() {
        status = HttpStatus.OK;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ResponseHandler setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public List getData() {
        return data;
    }

    public ResponseHandler setData(List data) {
        this.data = data;
        return this;
    }

    public Object getBean() {
        return bean;
    }

    public ResponseHandler setBean(Object bean) {
        this.bean = bean;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseHandler setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getToken() {
        return token;
    }

    public ResponseHandler setToken(String token) {
        this.token = token;
        return this;
    }

}
