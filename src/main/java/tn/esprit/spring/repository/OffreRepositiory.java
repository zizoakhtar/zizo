package tn.esprit.spring.repository;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import tn.esprit.spring.entities.*;

import java.util.List;
import java.util.Set;

@Component
@RepositoryRestResource
@CrossOrigin("*")
public interface OffreRepositiory extends JpaRepository<offre, Long> {
    @RestResource(path = "/byname")
    public List<offre> findByName(@Param("mc") String des );
    @RestResource(path = "/bydesignationPage")
    public Page<offre> findByName(@Param("mc") String des , Pageable pageable);
    @Query("SELECT u.id_offre FROM offre u WHERE u.id_offre = ?1")
    public Long findcategoriebyoffre(Long id);
    @Query("SELECT u.categorie FROM offre u WHERE u.id_offre = ?1")
    public categorie findcat(Long id);

    @Query("SELECT u FROM offre u where u.categorie = ?1")
    public List<offre> findoffresBycat(categorie cat);
    @Query("SELECT COUNT (u) FROM offre u ")
    public int countoffre();

}
