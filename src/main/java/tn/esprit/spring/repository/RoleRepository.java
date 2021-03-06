package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.entities.*;

import org.springframework.web.bind.annotation.CrossOrigin;


import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
public interface RoleRepository extends JpaRepository<Role,Long> {

Optional<Role> findByName(ERole name);

}
