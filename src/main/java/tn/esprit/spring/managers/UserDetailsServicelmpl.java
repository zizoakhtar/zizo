package tn.esprit.spring.managers;

import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.spring.entities.*;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.repository.UserRepository;
@Component
public class UserDetailsServicelmpl  implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found with username"+ username));
        return  UserDetailslmpl.build(user);
    }
}