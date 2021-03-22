package fr.greta.cda.dao;

import java.util.List;

import fr.greta.cda.model.Produit;

public interface ProduitDao {
    public Produit getProduit(int id);
    public List<Produit> getAllProduit(double min, double max);
	public List<Produit> findByMotCle(String motCle, double min, double max);
	public List<Produit> findByCategorie(int id_categorie, double min, double max);
	public List<Produit> findByMotCLeCategorie(String motCle, int id_categorie, double min, double max);
}

