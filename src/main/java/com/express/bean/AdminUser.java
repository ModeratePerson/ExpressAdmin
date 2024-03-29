package com.express.bean;

public class AdminUser {
    private String username;
    private String password;
    private String ip_address;
    private String phone_number;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public AdminUser(String username, String password, String ip_address, String phone_number) {
        this.username = username;
        this.password = password;
        this.ip_address = ip_address;
        this.phone_number = phone_number;
    }

    public AdminUser() {
    }
}
