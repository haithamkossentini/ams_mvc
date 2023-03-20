package com.sip.ams.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produit {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Libelle est obligatoire")
    @Column(name = "libelle")
    private String libelle;
    
    @NotBlank(message = "Photo Face est obligatoire")
    @Column(name = "photoFace")
    private String photoFace;
    
    @NotBlank(message = "Photo de profil est obligatoire")
    @Column(name = "photoProfil")
    private String photoProfil;
 
    @NotBlank(message = "Prix est obligatoire")
    @Column(name = "prix")
    private float prix;
    
    @NotBlank(message = "Description est obligatoire")
    @Column(name = "description")
    private String description;
    
    @NotBlank(message = "Quantite est obligatoire")
    @Column(name = "quantiteStock")
    private int quantiteStock;
    
    @NotBlank(message = "Prix Promotion est obligatoire")
    @Column(name = "prixPromotion")
    private float prixPromotion;
    
    @NotBlank(message = "Date d'expiration est obligatoire")
    @Column(name = "dateExpiration")
    private Date dateExpiration;
    
    /**** Many To One ****/
    @JsonIgnore	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fournisseur_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Fournisseur fournisseur;

    public Produit() {}
	public Produit( String libelle,String photoFace,String photoProfil,float prix,String description,int quantiteStock,float prixPromotion,Date dateExpiration) {
		super();
		this.libelle = libelle;
		this.photoFace = photoFace;
		this.photoProfil = photoProfil;
		this.prix = prix;
		this.description = description;
		this.quantiteStock = quantiteStock;
		this.prixPromotion = prixPromotion;
		this.dateExpiration = dateExpiration;
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getPhotoFace() {
		return photoFace;
	}
	public void setPhotoFace(String photoFace) {
		this.photoFace = photoFace;
	}
	public String getPhotoProfil() {
		return photoProfil;
	}
	public void setPhotoProfil(String photoProfil) {
		this.photoProfil = photoProfil;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantiteStock() {
		return quantiteStock;
	}
	public void setQuantiteStock(int quantiteStock) {
		this.quantiteStock = quantiteStock;
	}
	public float getPrixPromotion() {
		return prixPromotion;
	}
	public void setPrixPromotion(float prixPromotion) {
		this.prixPromotion = prixPromotion;
	}
	public Date getDateExpiration() {
		return dateExpiration;
	}
	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}
	public Fournisseur getFournisseur() {
		return fournisseur;
	}
	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	@Override
	public String toString() {
		return "Produit [id=" + id + ", libelle=" + libelle + ", photoFace=" + photoFace + ", photoProfil="
				+ photoProfil + ", prix=" + prix + ", description=" + description + ", quantiteStock=" + quantiteStock
				+ ", prixPromotion=" + prixPromotion + ", dateExpiration=" + dateExpiration + "]";
	}
    
    
}
