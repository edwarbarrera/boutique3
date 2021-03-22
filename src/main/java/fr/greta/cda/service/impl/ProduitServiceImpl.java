package fr.greta.cda.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import fr.greta.cda.dao.ProduitDao;
import fr.greta.cda.model.Produit;
import fr.greta.cda.service.ProduitService;

public class ProduitServiceImpl implements ProduitService {
    @Autowired
    private ProduitDao produitDao;
    @Override
    public Produit getProduit(int id) {
        return produitDao.getProduit(id);
    }
    @Override
    public List<Produit> getAllProduit(double min, double max) {
        return produitDao.getAllProduit(min, max);
    }
    public ProduitDao getProduitDao() {
        return produitDao;
    }
	@Override
	public List<Produit> findByMotCle(String motCle, double min, double max) {
		return produitDao.findByMotCle(motCle, min, max);
	}
	@Override
	public List<Produit> findByCategorie(int id_categorie, double min, double max) {
		return produitDao.findByCategorie(id_categorie, min, max);
	}
	@Override
	public List<Produit> findByMotCLeCategorie(String motCle, int id_categorie, double min, double max) {
		return produitDao.findByMotCLeCategorie(motCle, id_categorie, min, max);
	}
}
