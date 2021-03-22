package fr.greta.cda.model;

import java.io.Serializable;

public class Categorie implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3894741776119470316L;
	private int id_categorie;
	private String libelle;
	public Categorie() {
		super();
	}

	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Categorie [id_categorie=" + id_categorie + ", libelle=" + libelle + "]";
	}
	
	
}
