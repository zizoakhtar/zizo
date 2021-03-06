package tn.esprit.spring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "propositions")
public class proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_proposition;
    private  String propositionscanner ;
    private boolean validation;
    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name ="User_id")
    private  User user ;
    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name ="offre_ID")
    private offre offre ;
    private String Description;
    @Column(name = "type")
    private String type;


    public proposition(String propositionscanner, boolean validation, User user, String description, String type, String name) {
        this.propositionscanner = propositionscanner;
        this.validation = validation;
        this.user = user;
        Description = description;
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name="name")
    private String name ;
        public Long getId_proposition() {
        return id_proposition;
    }

    public void setId_proposition(Long id_proposition) {
        this.id_proposition = id_proposition;
    }

    public String getPropositionscanner() {
        return propositionscanner;
    }

    public void setPropositionscanner(String propositionscanner) {
        this.propositionscanner = propositionscanner;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public offre getOffre() {
        return offre;
    }

    public void setOffre(offre offre) {
        this.offre = offre;
    }

    public proposition(String propositionscanner, boolean validation, User user, offre offre) {
        this.propositionscanner = propositionscanner;
        this.validation = validation;
        this.user = user;
        this.offre = offre;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public proposition(String propositionscanner, boolean validation, User user,offre offre, String description) {
        this.propositionscanner = propositionscanner;
        this.validation = validation;
        this.user = user;
        this.offre = offre;
        Description = description;
    }

    public proposition(String propositionscanner, boolean validation, User user,offre offre, String description, String type, String name) {
        this.propositionscanner = propositionscanner;
        this.validation = validation;
        this.user = user;
        this.offre = offre;
        Description = description;
        this.type = type;
        this.name = name;
    }

    public proposition() {
    }

    public proposition(String propositionscanner, boolean validation, String description, String type, String name) {
        this.propositionscanner = propositionscanner;
        this.validation = validation;
        Description = description;
        this.type = type;
        this.name = name;
    }

}
