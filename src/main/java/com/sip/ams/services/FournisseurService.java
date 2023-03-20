package com.sip.ams.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.ams.entities.Fournisseur;
import com.sip.ams.repositories.FournisseurRepository;

@Service
public class FournisseurService {
    private final FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurService(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }
    public List<Fournisseur> getAllProvider(){
        return (List<Fournisseur>)fournisseurRepository.findAll();

    }

    public Fournisseur persistFournisseur(Fournisseur forunisseur)
    {
        /// autres traiteement
        return  this.fournisseurRepository.save(forunisseur);
    }
}
