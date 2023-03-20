package com.sip.ams.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.validation.Valid;

import com.sip.ams.repositories.FournisseurRepository;
import com.sip.ams.entities.Fournisseur;
@RestController
@RequestMapping("/rest/fournisseurs")
@CrossOrigin(origins="*")
public class RestFournisseurController {
	@Autowired
    private FournisseurRepository fournisseurRepository;

	 @GetMapping("/list")
	    public List<Fournisseur> getAllFournisseurs() {
	        return (List<Fournisseur>) fournisseurRepository.findAll();
	    }

	    @PostMapping("/add")
	    public Fournisseur createFournisseur(@Valid @RequestBody Fournisseur provider) {
	        return fournisseurRepository.save(provider);
	    }

	    @PutMapping("/{fournisseurId}")
	    public Fournisseur updateProvider(@PathVariable Long fournisseurId, @Valid @RequestBody Fournisseur fournisseurRequest) {
	        return fournisseurRepository.findById(fournisseurId).map(fournisseur -> {
	        	fournisseur.setNom(fournisseurRequest.getNom());
	        	fournisseur.setEmail(fournisseurRequest.getEmail());
	        	fournisseur.setAddress(fournisseurRequest.getAddress());
	            return fournisseurRepository.save(fournisseur);
	        }).orElseThrow(() -> new IllegalArgumentException("FournisseurId " + fournisseurId + " not found"));
	    }


	    @GetMapping("/{fournisseurId}")
	    public Fournisseur getProvider(@PathVariable Long fournisseurId) {
	        Optional<Fournisseur> f = fournisseurRepository.findById(fournisseurId);
	        return f.orElseThrow(()->new NoSuchElementException("Element introuvable!"));
	    }

	    @DeleteMapping("/{fournisseurId}")
	    public  Fournisseur deleteProvider(@PathVariable Long fournisseurId) {

	        Optional<Fournisseur> f = fournisseurRepository.findById(fournisseurId);

	        if(f.isPresent())
	        {
	        	fournisseurRepository.delete(f.get());
	        }
	        else throw new NoSuchElementException("Element introuvable!");
	        return f.get();
	    }
}
