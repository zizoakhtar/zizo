package tn.esprit.spring.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username"),@UniqueConstraint(columnNames = "email")})
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@NotBlank
@Size(max = 20)
    private String username;
@NotBlank
@Size(max = 50)
@Email
private  String email ;
@NotBlank
@Size(max = 200)
private String password;

@Transient
private String passwordConfirm;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="user",cascade={CascadeType.REMOVE})
    @JsonBackReference
    private List<proposition> listpropo =new ArrayList<>() ;


    @JsonIgnore

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name ="id_entreprise")
    private entreprise entrepriseuser;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy="useravis",cascade={CascadeType.REMOVE})
    @JsonBackReference
    private List<avis> avisList=new ArrayList<>() ;

    public List<avis> getAvisList() {
        return avisList;
    }

    public void setAvisList(List<avis> avisList) {
        this.avisList = avisList;
    }

    @ManyToMany(fetch =FetchType.LAZY)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn (name = "role_id"))
    private Set<Role> role=new HashSet<>();

    public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 200) String password, String passwordConfirm, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.role = roles;
    }

    public User( String username,String email,String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email,  String password, Set<Role> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = roles;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return role;
    }

    public entreprise getEntrepriseuser() {
        return entrepriseuser;
    }

    public void setEntrepriseuser(entreprise entrepriseuser) {
        this.entrepriseuser = entrepriseuser;
    }

    public void setRoles(Set<Role> roles) {
        this.role = roles;
    }

    private boolean valider;

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    public User(@NotBlank @Size(max = 20) String username, @NotBlank @Size(max = 50) @Email String email, @NotBlank @Size(max = 200) String password, String passwordConfirm, entreprise entrepriseuser, List<avis> avisList, Set<Role> role, boolean valider) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.entrepriseuser = entrepriseuser;
        this.avisList = avisList;
        this.role = role;
        this.valider = valider;
    }

    public List<proposition> getListpropo() {
        return listpropo;
    }

    public void setListpropo(List<proposition> listpropo) {
        this.listpropo = listpropo;
    }
}

