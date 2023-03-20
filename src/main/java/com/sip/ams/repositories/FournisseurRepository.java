package com.sip.ams.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sip.ams.entities.Fournisseur;
import com.sip.ams.entities.Produit;

@Repository
public interface FournisseurRepository extends CrudRepository<Fournisseur,Long>{
	  @Query("FROM Produit p WHERE p.fournisseur.id = ?1")
	    List<Produit> findProduitsByFournisseur(long id);
}


