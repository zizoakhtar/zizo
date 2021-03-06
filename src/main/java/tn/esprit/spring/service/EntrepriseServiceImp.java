package tn.esprit.spring.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.repository.OffreRepositiory;
import tn.esprit.spring.repository.UserRepository;
@Service
public class EntrepriseServiceImp implements EntrepriseService{
    @Autowired
    UserRepository userRepository ;
    //akther chariket 3amlin proposition
    public List<statistiqueEntreprise> statequipesujet()
    {
        List<statistiqueEntreprise> stats=new ArrayList<>();
        List <User> Users=(List<User>) userRepository.findByrolefournisseur();
        for(User e:Users)
        {
            statistiqueEntreprise stat =new statistiqueEntreprise();
            stat.setNom(e.getEntrepriseuser().getName_entreprise());
            stat.setNbproposition(e.getListpropo().size());
            stats.add(stat);
        }
        return stats;
    }
    @Autowired
    OffreRepositiory offreRepositiory ;
    //akther offrouet 3amlin 3lihom proposition
    public List<statistiqueOffre> stateoffre()
    {
        List<statistiqueOffre> stats=new ArrayList<>();
        List <offre> offres=(List<offre>) offreRepositiory.findAll();
        for(offre e:offres)
        {
            statistiqueOffre stat =new statistiqueOffre();
            stat.setNom(e.getName());
            stat.setNbpropo(e.getListepropo().size());
            stats.add(stat);
        }
        return stats;
    }


}
