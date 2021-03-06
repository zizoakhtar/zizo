package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;import tn.esprit.spring.repository.*;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.secuirty.*;
import tn.esprit.spring.jwt.*;
import tn.esprit.spring.service.*;
import tn.esprit.spring.request.*;
import tn.esprit.spring.managers.*;
import tn.esprit.spring.config.*;
import tn.esprit.spring.responce.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;
    @GetMapping(value = "/listuser" )
    public List<User> usersList(){
        return  userRepository.findAll();

    }

    /* ////////////////////////////find////////////////////// */
    @GetMapping(value = "/user/{id}" )
    public User user(@PathVariable(name = "id") Long id ) {
        return userRepository.findById(id).get();
    }

    @DeleteMapping(value = "/deleteuser/{id}" )
    public void Delete(@PathVariable(name = "id") Long id ) {
        userRepository.deleteById(id);

    }
    Set<Role> roles = new HashSet<>();

    @GetMapping(value = "/userbyrole" )
    public List<User> usersListbyrole(){
        return  userRepository.findByRole(false);

    }



}
