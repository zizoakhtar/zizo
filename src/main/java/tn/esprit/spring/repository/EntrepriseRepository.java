package tn.esprit.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.*;
import java.util.List;
import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<entreprise, Long> {
    @Query("SELECT u.categories FROM entreprise u WHERE u.id_entreprise = ?1")
    public categorie findcat(Long id);
    Optional<entreprise> findByName(String name);

    @Query("SELECT u FROM entreprise u where u.categories = ?1")
    public List<entreprise> finentrepriseBycat(categorie cat);

    @Query("SELECT count (u) FROM entreprise u ")
    public int countentreprise();
    @Query("SELECT u.entrepriseuser.name_entreprise FROM User u where u.id = ?1")
    public String findnameentreprisebyuser(long id);
    @Query("SELECT u.listeUser FROM entreprise u where u.type = ?1")
    public List<User> finduserbyentreprise(String id);
    @Transactional
    @Modifying
    @Query("DELETE  FROM entreprise u where u.type = ?1")
    public void deletebytype(String id);
    @Query("SELECT u.entrepriseuser FROM User u where u.id = ?1")
    public entreprise finentreprisebyuser(long id);

}
