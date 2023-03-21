package com.sip.ams.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sip.ams.entities.Fournisseur;
import com.sip.ams.repositories.FournisseurRepository;
import com.sip.ams.services.FournisseurService;
@Controller
@RequestMapping("/fournisseurs")
public class FournisseurController {

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
    	List<Fournisseur> lp = this.fournisseurService.getAllProvider();

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
    public String addProvider(@Valid Fournisseur fournisseur, BindingResult result) {
        if (result.hasErrors()) {
            return "fournisseur/addFournisseur";
        }
        if(fournisseur.getNom()=="")
        	fournisseur.setNom(null);
        //providerRepository.save(provider);
        this.fournisseurService.persistFournisseur(fournisseur);
        return "redirect:list";
    }
}
