package com.express.bean;

public class Message {
    public String result;
    public int status;
    public Object data;

    public Message() {
    }

    public Message(String result, int status, Object data) {
        this.result = result;
        this.status = status;
        this.data = data;
    }

    public Message(String result, int status) {
        this.result = result;
        this.status = status;
    }

    public Message(int status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Message(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
