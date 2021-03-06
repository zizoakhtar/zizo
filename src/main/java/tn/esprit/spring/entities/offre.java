package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@Table(name = "offres")
public class offre {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_offre;
    private String name  ;
    private LocalDate date;
    private int quantite ;
    private  String description ;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @JsonIgnore
    @OneToMany(mappedBy="offre",cascade={CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<proposition> listepropo=new ArrayList<>() ;

    public Long getId_offre() {
        return id_offre;
    }

    public void setId_offre(Long id_offre) {
        this.id_offre = id_offre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NaturalId(mutable=true)
    @Column(name = "categorie", unique=true)
    @Enumerated(EnumType.STRING)
    private categorie categorie;

  /*  @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @ManyToMany(fetch =FetchType.LAZY)
    @JoinTable(name = "offre_categorie",joinColumns = @JoinColumn(name = "offre_id"),
            inverseJoinColumns = @JoinColumn (name = "categorie_id"))
    private Set<Ecategorie> categories=new HashSet<>();

*/



    public List<proposition> getListepropo() {
        return listepropo;
    }

    public void setListepropo(List<proposition> listepropo) {
        this.listepropo = listepropo;
    }

    public categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(categorie categorie) {
        this.categorie = categorie;
    }

    public offre(String name, int quantite, String description, List<proposition> listepropo,categorie categorie) {
        this.name = name;
        this.quantite = quantite;
        this.description = description;
        this.listepropo = listepropo;
        this.categorie = categorie;
    }

    public offre(String name, int quantite, String description, categorie categorie) {
        this.name = name;
        this.quantite = quantite;
        this.description = description;
        this.categorie = categorie;
    }

    public offre() {
    }

    public offre(String name, LocalDate date, int quantite, String description, categorie categorie) {
        this.name = name;
        this.date = date;
        this.quantite = quantite;
        this.description = description;
        this.categorie = categorie;
    }
}
