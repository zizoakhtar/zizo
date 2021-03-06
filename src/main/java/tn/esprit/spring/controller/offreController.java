package tn.esprit.spring.controller;

import org.junit.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;import tn.esprit.spring.repository.*;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.secuirty.*;
import tn.esprit.spring.jwt.*;
import tn.esprit.spring.service.*;
import tn.esprit.spring.request.*;
import tn.esprit.spring.managers.*;
import tn.esprit.spring.config.*;
import tn.esprit.spring.responce.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
@RestController
@RequestMapping("/offre")
@CrossOrigin(origins = "*", maxAge = 3600L)

public class offreController {
@Autowired
    private OffreRepositiory offreRepositiory ;


    /* ////////////////////////////afficher////////////////////// */
    @GetMapping(value = "/listoffre" )
    public List<offre> produitList(){
        return  offreRepositiory.findAll();

    }
    /////////////////findoffrebycateg////////////////
    @GetMapping(value = "/listoffrebycat" )
    public List<offre> findoffrebycat(categorie categorie){
        return  offreRepositiory.findoffresBycat(categorie);

    }
    @GetMapping(value = "/countoffre" )
    public int countoffre(){
        return  offreRepositiory.countoffre();

    }

    /* ////////////////////////////find////////////////////// */
    @GetMapping(value = "/offre/{id}" )
    public offre offre(@PathVariable(name = "id") Long id ) {
        return offreRepositiory.findById(id).get();
    }




    /* ////////////////////////////update////////////////////// */
    @RequestMapping(value = "/Update/{id}", method = RequestMethod.PUT)
    public offre Updateoffre(@PathVariable(name = "id") Long id ,@RequestBody offre p) {
        System.out.println(offreRepositiory.findcategoriebyoffre(id));
        System.out.println(offreRepositiory.findcat(id));
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(dtf.format(localDate)); //2016/11/16
        p.setDate(localDate);
        p.setId_offre(id);


        return offreRepositiory.save(p);

    }

    @RequestMapping(value = "/ajout", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public offre addEquipe(@RequestBody offre u) {
        System.out.println("(Service Side) Creating equipe : ");
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        System.out.println(dtf.format(localDate)); //2016/11/16

        LocalDate now = LocalDate.now();
        LocalDate sixDaysBehind = now.minusDays(6);

        Period period = Period.between(now, sixDaysBehind);
        int diff = period.getDays();

        Assert.assertEquals(diff, 6);
        u.setDate(localDate);
        offre equipe = offreRepositiory.save(u);



        return equipe;
    }

    public void deleteauto(){
        List<offre>li=  offreRepositiory.findAll();

        for(offre a : li){

            LocalDate now = LocalDate.now();
         //   LocalDate sixDaysBehind = now.minusDays(6);

            Period period = Period.between( a.getDate(),now);
            System.out.println(period);
            int diff = period.getDays();
            System.out.println(diff);
        if (diff== 22){

            Delete(a.getId_offre());
        }

        }
    }

    @DeleteMapping(value = "/delete/{id}" )
    public void Delete(@PathVariable(name = "id") Long id ) {
        offreRepositiory.deleteById(id);

    }





    ////////////////////zeyda classs//////////////////

    /*    @Autowired
    CategorieRepository categorieRepository;


 @PostMapping(value = "/ajout" )
    public ResponseEntity<?> save(@RequestBody categorierequest signUpRequest){
        Set<String> strRoles = signUpRequest.getCategories();
        Set<Ecategorie> Ecategories = new HashSet<>();
        offre offre = new offre(signUpRequest.getName(),
                signUpRequest.getQuantite(),signUpRequest.getDescription());
        offreRepositiory.save(offre);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

        if (strRoles == null) {
            Ecategorie userRole = categorieRepository.findByName(categorie.materiel)
                    .orElseThrow(() -> new RuntimeException("Error: categorie is not found."));
            Ecategories.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "fourniture":
                        Ecategorie fournitureRole = categorieRepository.findByName(categorie.fourniture)
                                .orElseThrow(() -> new RuntimeException("Error: categorie is not found."));
                        Ecategories.add(fournitureRole);
                        break;
                    case "informatique":
                        Ecategorie modRole =categorieRepository.findByName(categorie.informatique)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        Ecategories.add(modRole);

                        break;
                    case "education":
                        Ecategorie education =categorieRepository.findByName(categorie.education)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        Ecategories.add(education);

                        break;
                    default:
                        Ecategorie materiel = categorieRepository.findByName(categorie.materiel)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        Ecategories.add(materiel);
                }
            });
        }*/
    //offre.setCategories(Ecategories);

    //}
}
