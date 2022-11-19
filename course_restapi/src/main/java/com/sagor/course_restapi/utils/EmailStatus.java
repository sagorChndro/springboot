package com.sagor.course_restapi.utils;

public class EmailStatus {
    private static final String SUCCESS="SUCCESS";
    private static final String ERROR="ERROR";

    private String[] to;
    private String subject;
    private String body;
    private String status;
    private String errorMessage;
    public EmailStatus(String[] to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public EmailStatus success(){
        this.status = SUCCESS;
        return this;
    }

    public EmailStatus error(){
        this.status = ERROR;
        return this;
    }

    public Boolean isSuccess(){
        return SUCCESS.equals(this.status);
    }

    public Boolean isError(){
        return ERROR.equals(this.status);
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
