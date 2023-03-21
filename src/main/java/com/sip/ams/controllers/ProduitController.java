package com.sip.ams.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.sip.ams.repositories.ProduitRepository;
import com.sip.ams.services.FournisseurService;
@Controller
@RequestMapping("/produits")
public class ProduitController {
	 public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
		static String trouve=null;
		
	private final ProduitRepository produitRepository;
	private final FournisseurRepository fournisseurRepository;

	 @Autowired
	 public ProduitController(FournisseurRepository fournisseurRepository, ProduitRepository produitRepository) {
	     this.fournisseurRepository = fournisseurRepository;
	     this.produitRepository=produitRepository;
	 }
	 @GetMapping("list")
	 public String listProduits(Model model) {
	 	//model.addAttribute("articles", null);
	 	List<Produit> la = (List<Produit>)produitRepository.findAll();
	 	if(la.size()==0)
	 		la = null;
	     model.addAttribute("produits", la);
	     model.addAttribute("trouve", trouve); 
	     return "produit/listProduits";
	 }
	  @GetMapping("add")
	    public String showAddProduitForm(Model model) {
	    	
	    	model.addAttribute("fournisseurs", fournisseurRepository.findAll());
	    	model.addAttribute("produit", new Produit());
	        return "produit/addProduit";
	    }
	    
	    @PostMapping("add")
	    //@ResponseBody
	    public String addProduit(@Valid Produit produit, BindingResult result, @RequestParam(name = "fournisseurId", required = true) Long f,
	                             @RequestParam("files") MultipartFile[] files, @RequestParam("files") MultipartFile[] files2,@RequestParam("dateExpiration") String d
	    ) {
	    	
	    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    	Date date=null;
			try {
				date = format.parse(d);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	produit.setDateExpiration(date);
	    	Fournisseur fournisseur = fournisseurRepository.findById(f)
	                .orElseThrow(()-> new IllegalArgumentException("Invalid fournisseur Id:" + f));
	    	produit.setFournisseur(fournisseur);

	        /// part upload

	        // upload du ficher
	        MultipartFile file = files[0];
	        Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());

	        try {
	            Files.write(fileNameAndPath, file.getBytes());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Stockage du name du ficher dans la base
	        StringBuilder fileName = new StringBuilder();
	        fileName.append(file.getOriginalFilename());
	        produit.setPhotoFace(fileName.toString());

	        System.out.println(produit);
	        
	        MultipartFile file2 = files[0];
	        Path fileNameAndPath2 = Paths.get(uploadDirectory, file2.getOriginalFilename());

	        try {
	            Files.write(fileNameAndPath2, file2.getBytes());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Stockage du name du ficher dans la base
	        StringBuilder fileName2 = new StringBuilder();
	        fileName2.append(file2.getOriginalFilename());
	        produit.setPhotoProfil(fileName2.toString());

	        produitRepository.save(produit);
	    	 return "redirect:list";
	    	
	    	//return article.getLabel() + " " +article.getPrice() + " " + p.toString();
	    }
	    @GetMapping("show/{id}")
	    public String showProduitDetails(@PathVariable("id") long id, Model model) {
	    	Produit produit = produitRepository.findById(id)
	                .orElseThrow(()->new IllegalArgumentException("Invalid produit Id:" + id));

	        model.addAttribute("produit", produit);

	        return "produit/showProduit";
	    }
	    @GetMapping("edit/{id}")
	    public String showProduitFormToUpdate(@PathVariable("id") long id, Model model) {
	    	Produit produit = produitRepository.findById(id)
	            .orElseThrow(()->new IllegalArgumentException("Invalid provider Id:" + id));
	    	
	        model.addAttribute("produit", produit);
	        model.addAttribute("fournisseurs", fournisseurRepository.findAll());
	        model.addAttribute("idFournisseur", produit.getFournisseur().getId());
	        
	        return "produit/updateProduit";
	    }
	    @PostMapping("update")
	    public String updateProduit( @Valid Produit produit, BindingResult result,
	        Model model, @RequestParam(name = "fournisseurId", required = false) Long p) {
	        if (result.hasErrors()) {
	        	
	            return "produit/updateProduit";
	        }
	        
	        Fournisseur fournisseur = fournisseurRepository.findById(p)
	                .orElseThrow(()-> new IllegalArgumentException("Invalid fournisseur Id:" + p));
	        
	        produit.setFournisseur(fournisseur);
	    	
	        produitRepository.save(produit);
	        return "redirect:list";
	    }

	    @GetMapping("delete/{id}")
	    public String deleteProduit(@PathVariable("id") long id, Model model) {	    		   
	        Produit produit = produitRepository.findById(id)
	            .orElseThrow(()-> new IllegalArgumentException("Invalid provider Id:" + id));	       
	        produitRepository.delete(produit);	       
	        return "redirect:../list";
	    }
}
