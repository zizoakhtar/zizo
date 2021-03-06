package tn.esprit.spring.controller;

import org.junit.experimental.categories.Categories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;import tn.esprit.spring.repository.*;
import tn.esprit.spring.entities.*;
import tn.esprit.spring.secuirty.*;
import tn.esprit.spring.jwt.*;
import tn.esprit.spring.service.*;
import tn.esprit.spring.storage.StorageService;
import tn.esprit.spring.request.*;
import tn.esprit.spring.managers.*;
import tn.esprit.spring.config.*;
import tn.esprit.spring.responce.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class EntrepriseController {
    @Autowired
    private EntrepriseRepository entrepriseRepository ;
    @Autowired
    private EntrepriseRepository offreRepositiory ;
    @Autowired
    EntrepriseService entrepriseService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    /* ////////////////////////////afficher////////////////////// */
    @GetMapping(value = "/getAll" )
    public List<entreprise> entrepriseList(){
        return  offreRepositiory.findAll();

    }
    @GetMapping(value = "/countentreprise" )
    public int countentreprise(){
        return  offreRepositiory.countentreprise();

    }


    /* ////////////////////////////find////////////////////// */
    @GetMapping(value = "/listentreprise/{id}" )
    public entreprise entreprise
    (@PathVariable(name = "id") Long id ) {
        return offreRepositiory.findById(id).get();
    }

    @GetMapping(value = "/entreprisebyuser/{id}" )
    public String nameentreprisebyuser
            (@PathVariable(name = "id") Long id ) {
        return offreRepositiory.findnameentreprisebyuser(id);
    }

    @GetMapping(value = "/entreprisebyuser2/{id}" )
    public entreprise entreprisebyuser(@PathVariable(name = "id") Long id ) {
        return offreRepositiory.finentreprisebyuser(id);
    }

    /* ////////////////////////////update////////////////////// */
    @RequestMapping(value = "/Updateentreprise/{id}", method = RequestMethod.PUT)
    public entreprise Updateentreprise(@PathVariable(name = "id") Long id ,@RequestBody entreprise p) {
        System.out.println(offreRepositiory.findcat(id));
        p.setCategories(offreRepositiory.findcat(id));
        p.setId_entreprise(id);
        p.setCategories(offreRepositiory.findcat(id));

        return offreRepositiory.save(p);

    }
    Set<Role> roles = new HashSet<>();
    /* ////////////////////////////update////////////////////// */
    @RequestMapping(value = "/Updateroleuser/{type}", method = RequestMethod.PUT)
    public void  Updateusertoroleuser(@PathVariable(name = "type") String id_entreprise) {

        List<User>a = entrepriseRepository.finduserbyentreprise(id_entreprise);
        for(User b : a) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            b.setId(b.getId());

            b.setEmail(b.getEmail());
            b.setPassword(b.getPassword());
            b.setUsername(b.getUsername());
            b.setValider(true);
            b.setRoles(roles);
            b.setEntrepriseuser(null);
            userRepository.save(b);
        }

    }
/*
    @RequestMapping(value = "/ajoutentreprise", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public entreprise addEquipe(@RequestBody entreprise u) {
        System.out.println("(Service Side) Creating equipe : ");
        entreprise equipe = offreRepositiory.save(u);
        return equipe;
    }
*/
    @DeleteMapping(value = "/deleteentreprise/{type}" )
    public void Delete(@PathVariable(name = "type") String id ) {
        offreRepositiory.deletebytype(id);

    }
    /////////////////////////////////////////////////////////////////////////////////////////
  /*  @PostMapping("/upload")
    public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file,categorie categorie,String name_entreprise) throws IOException {

        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        entreprise img = new entreprise(compressBytes(file.getBytes()),file.getOriginalFilename(),file.getContentType(),name_entreprise,categorie);
        offreRepositiory.save(img);
        return ResponseEntity.status(HttpStatus.OK);
    }
    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }*/
  /*  @GetMapping(path = { "/get/{imageName}" })
    public entreprise getImage(@PathVariable("imageName") String imageName) throws IOException {

        final Optional<entreprise> retrievedImage = offreRepositiory.findByName(imageName);
        entreprise img = new entreprise(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPatente()));
        return img;
    }*/
    // uncompress the image bytes before returning it to the angular application
  /*  public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }*/   ////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Autowired
    StorageService storageService;

    List<String> files = new ArrayList<String>();

    @PostMapping("/post")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file ,@RequestParam("logo") MultipartFile logo , String name_entreprise ,String descprition, categorie categorie )
    {
        String message = "";
        try {

            storageService.store(file);
            storageService.store(logo);
            files.add(file.getOriginalFilename());
            files.add(logo.getOriginalFilename());
            message = "You successfully uploaded " + file.getOriginalFilename() + "!";


            entreprise e = new entreprise(file.getName(),file.getContentType(),file.getOriginalFilename(),name_entreprise,categorie,descprition,logo.getOriginalFilename());
            offreRepositiory.save(e);
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }



    }

    @GetMapping("/getallfiles")
    public List<String> getListFiles( ) {
        List<String> fileNames = files
                .stream().map(fileName -> MvcUriComponentsBuilder
                        .fromMethodName(EntrepriseController.class, "getFile", fileName).build().toString())
                .collect(Collectors.toList());
        return files;


    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
    @GetMapping(value = "/listentreprisebycat" )
    public List<entreprise> findenrtreprisebycat(categorie categorie){
        return  offreRepositiory.finentrepriseBycat(categorie);

    }
    @RequestMapping(value = "/stat-entreprise", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<statistiqueEntreprise> stateentreprise(){
        return entrepriseService.statequipesujet();
    }
    @RequestMapping(value = "/stat-offre", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public List<statistiqueOffre> stateoffre(){
        return entrepriseService.stateoffre();
    }



}
