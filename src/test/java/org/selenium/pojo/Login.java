package org.selenium.pojo;

public class Login {
    private String username;
    private String password;
    private String email;

    public Login(){};
    public Login(String username, String password){
        this.username = username;
        this.password = password;
    }
    public Login(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public String getUsername() {
        return username;
    }

    public Login setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Login setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Login setEmail(String email) {
        this.email = email;
        return this;
    }
}
