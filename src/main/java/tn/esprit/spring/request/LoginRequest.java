package tn.esprit.spring.request;

public class LoginRequest {
private  String Username ;
private  String password ;

    public LoginRequest(String username, String password) {
        Username = username;
        this.password = password;
    }

    public LoginRequest() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
