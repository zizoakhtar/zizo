package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;import tn.esprit.spring.entities.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    @Query("SELECT u FROM User u where u.valider  = ?1 ")
    public List<User> findByRole( boolean valider);

    @Query("SELECT u FROM User u where u.entrepriseuser  IS NOT NULL ")
    public List<User> findByrolefournisseur( );


    }