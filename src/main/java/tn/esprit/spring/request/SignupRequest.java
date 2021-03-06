package tn.esprit.spring.request;

import java.util.List;
import java.util.Set;

public class SignupRequest{
    private  String Username ;
    private  String password ;
    private String email ;
    private Set<String> role ;
    public SignupRequest(String username, String password, String email) {
        Username = username;
        this.password = password;
        this.email = email;
    }

    public SignupRequest() {
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Set<String> getRoles() {
        return role;
    }

    public void setRoles(Set<String> roles) {
        this.role = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
