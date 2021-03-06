package tn.esprit.spring.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entreprises")
public class entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_entreprise;
    @Lob
    @Column(name = "patente")
    private String patente;
    @Column(name = "type")
    private String type;
    @Column(name = "name_entreprise")
    private String name_entreprise;
    @Column(name = "description")
    private String description;
    @Column(name = "logo")
    private String logo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public categorie getCategories() {
        return categories;
    }
    public String getName() {
        return name;
    }
    public void setCategories(categorie categories) {
        this.categories = categories;
    }

    @NaturalId(mutable=true)
    @Column(name = "categorie", unique=true)
    @Enumerated(EnumType.STRING)
    private categorie categories ;
    @OneToMany(mappedBy="entrepriseuser",cascade={CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<User> listeUser=new ArrayList<>() ;

    public Long getId_entreprise() {
        return id_entreprise;
    }

    public void setId_entreprise(Long id_entreprise) {
        this.id_entreprise = id_entreprise;
    }

    public List<User> getListeUser() {
        return listeUser;
    }

    public void setListeUser(List<User> listeUser) {
        this.listeUser = listeUser;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public entreprise(String patente, String name, categorie categories) {
        this.patente = patente;
        this.name = name;
        this.categories = categories;
    }
    @Column(name = "name")
    private String name;



    public entreprise(String patente, categorie categories, String name) {
        this.patente = patente;
        this.categories = categories;
        this.name = name;
    }

    public entreprise() {
    }

    public entreprise(String patente,String name,  String type, String name_entreprise, categorie categories,String description , String logo) {
        this.patente = patente;
        this.type = type;
        this.name_entreprise = name_entreprise;
        this.categories = categories;
        this.name = name;
        this.description=description;
        this.logo=logo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName_entreprise() {
        return name_entreprise;
    }



    public void setName_entreprise(String name_entreprise) {
        this.name_entreprise = name_entreprise;
    }



}
