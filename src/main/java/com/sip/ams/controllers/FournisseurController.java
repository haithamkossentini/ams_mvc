package com.sip.ams.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sip.ams.entities.Fournisseur;
import com.sip.ams.entities.Produit;
import com.sip.ams.repositories.FournisseurRepository;
import com.sip.ams.services.FournisseurService;
@Controller
@RequestMapping("/fournisseurs")
public class FournisseurController {
	 public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
		static String trouve=null;
		
	@Autowired
	private final FournisseurRepository fournisseurRepository;
	
    private final FournisseurService fournisseurService;

    @Autowired
    public FournisseurController(FournisseurRepository fournisseurRepository, FournisseurService fournisseurService) {
        this.fournisseurRepository = fournisseurRepository;
        this.fournisseurService = fournisseurService;
    }


    @GetMapping("list")
    //@ResponseBody
    public String listFournisseurs(Model model) {
    	List<Fournisseur> lp = this.fournisseurService.getAllFournisseur();

        model.addAttribute("fournisseurs", lp);
        
        return "fournisseur/listFournisseurs";

    }
    @GetMapping("add")
    public String showAddFournisseurForm(Model model) {
    	Fournisseur fournisseur = new Fournisseur();// object dont la valeur des attributs par defaut
    	model.addAttribute("fournisseur", fournisseur);
        return "fournisseur/addFournisseur";
    }
    
    @PostMapping("add")
    public String addFournisseur(@Valid Fournisseur fournisseur, BindingResult result,
            @RequestParam("files") MultipartFile[] files) {
      
      
    	
    	StringBuilder fileName = new StringBuilder();
    	MultipartFile file = files[0];
    	Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
    	
    	fileName.append(file.getOriginalFilename());
		  try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

        fournisseur.setLogo(fileName.toString());
        
        this.fournisseurService.persistFournisseur(fournisseur);
   	 	//fournisseurRepository.save(fournisseur);

        return "redirect:list";
    }
    @GetMapping("show/{id}")
    public String showProvider(@PathVariable("id") long id, Model model) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid provider Id:" + id));
        List<Produit> produits = fournisseurRepository.findProduitsByFournisseur(id);
        for (Produit p : produits)
            System.out.println("Produit = " + p.getLibelle());

        model.addAttribute("produits", produits);
        model.addAttribute("fournisseur", fournisseur);
        return "fournisseur/showFournisseur";
    }

    @GetMapping("edit/{id}")
    public String showFournisseurFormToUpdate(@PathVariable("id") long id, Model model) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid fournisseur Id:" + id));
        
        model.addAttribute("fournisseur", fournisseur);
        
        return "fournisseur/updateFournisseur";
    }


    
    @PostMapping("update")
    public String updateFournisseur(@Valid Fournisseur fournisseur, BindingResult result, Model model) {
    	
    	if (result.hasErrors()) {
           return "fournisseur/updateFournisseur";
        }
    	
    	fournisseurRepository.save(fournisseur);
    	return"redirect:list";
    	
    }
    
    @GetMapping("delete/{id}")
    public String deleteFournisseur(@PathVariable("id") long id, Model model) {
    	
    	
    	//long id2 = 100L;
    	
        Fournisseur fournisseur = fournisseurRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + id));
        
        //System.out.println("suite du programme...");
        
        fournisseurRepository.delete(fournisseur);
        
        //model.addAttribute("providers", providerRepository.findAll());
        //return "provider/listProviders";
        return "redirect:../list";
    }
}
