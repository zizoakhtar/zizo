package tn.esprit.spring.entities;

public class propositionentreprise {

    private  String propositionscanner ;
    private boolean validation;
    private  String Description ;
    private  String name_entreprise;
    private String image_entreprise;
    private Long id_proposition;
    private String name;
    public String getPropositionscanner() {
        return propositionscanner;
    }

    public void setPropositionscanner(String propositionscanner) {
        this.propositionscanner = propositionscanner;
    }

    public Long getId_proposition() {
        return id_proposition;
    }

    public void setId_proposition(Long id_proposition) {
        this.id_proposition = id_proposition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isValidation() {
        return validation;
    }

    public void setValidation(boolean validation) {
        this.validation = validation;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getName_entreprise() {
        return name_entreprise;
    }

    public void setName_entreprise(String name_entreprise) {
        this.name_entreprise = name_entreprise;
    }

    public String getImage_entreprise() {
        return image_entreprise;
    }

    public void setImage_entreprise(String image_entreprise) {
        this.image_entreprise = image_entreprise;
    }
}
