package com.sip.ams.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sip.ams.entities.Produit;


@Repository
public interface ProduitRepository extends CrudRepository<Produit,Long>{

}
