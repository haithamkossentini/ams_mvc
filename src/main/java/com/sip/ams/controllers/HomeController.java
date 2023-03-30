package com.sip.ams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sip.ams.repositories.FournisseurRepository;
import com.sip.ams.repositories.ProduitRepository;


@Controller
public class HomeController {
	private final ProduitRepository produitRepository;
	private final FournisseurRepository fournisseurRepository;
//	private final UserRepository userRepository;
	//private final RoleRepository roleRepository;
	 @Autowired
	    public HomeController(ProduitRepository produitRepository, FournisseurRepository fournisseurRepository) {
	        this.produitRepository = produitRepository;
	        this.fournisseurRepository = fournisseurRepository;
	        //this.userRepository = userRepository;
	        //this.roleRepository = roleRepository;
	    }
	@RequestMapping("/home")
	//@ResponseBody
	public String home(Model model) {
	//	return "Welcome";
    	long nbrArticles =  produitRepository.count();
    	long nbrProviders =  fournisseurRepository.count();
    	model.addAttribute("nbrArticles", nbrArticles);
    	model.addAttribute("nbrProviders", nbrProviders);
		return "front/index.html";
	}
	public String login() {
		return "";
	}
	public String registration() {
		return "";
	}
}
