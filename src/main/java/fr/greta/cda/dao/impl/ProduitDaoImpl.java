package fr.greta.cda.dao.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.greta.cda.dao.ProduitDao;
import fr.greta.cda.model.Produit;
public class ProduitDaoImpl implements ProduitDao {
    @Autowired
    private SessionFactory sessionFactory;
    
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    @Transactional
    @Override
    public Produit getProduit(int id) {
    	Produit produit = (Produit) sessionFactory.getCurrentSession().get(Produit.class, id);
        return produit;
    }
    
    @Transactional
    @Override
    public List<Produit> getAllProduit(double min, double max) {
    	@SuppressWarnings("unchecked")
		List<Produit> produits = (List<Produit>) sessionFactory.getCurrentSession()
                .createQuery("select p from Produit p where p.prix_actuel >= :min and p.prix_actuel <= :max")
                .setParameter("min", min)
                .setParameter("max", max)
                .list();

		return produits;
	}
    

    
    @Transactional
	@Override
	public List<Produit> findByMotCle(String motCle, double min, double max) {
    	@SuppressWarnings("unchecked")
		List<Produit> produits = (List<Produit>) sessionFactory.getCurrentSession()
                .createQuery("select p from Produit p where (p.nom like concat('%',TRIM(:nom),'%') or p.description like concat('%',TRIM(:description),'%')) and p.prix_actuel >= :min and p.prix_actuel <= :max")
                .setParameter("nom", motCle)
                .setParameter("description", motCle)
                .setParameter("min", min)
                .setParameter("max", max)
                .list();

		return produits;
	}
    
    @Transactional
	@Override
	public List<Produit> findByCategorie(int id_categorie, double min, double max) {
		@SuppressWarnings("unchecked")
		List<Produit> produits = (List<Produit>) sessionFactory.getCurrentSession()
                .createQuery("select p from Produit p where p.id_categorie = :categorie and p.prix_actuel >= :min and p.prix_actuel <= :max")
                .setParameter("categorie", id_categorie)
                .setParameter("min", min)
                .setParameter("max", max)
                .list();

		return produits;
	}
    
    @Transactional
	@Override
	public List<Produit> findByMotCLeCategorie(String motCle, int id_categorie, double min, double max) {
		@SuppressWarnings("unchecked")
		List<Produit> produits = (List<Produit>) sessionFactory.getCurrentSession()
                .createQuery("select p from Produit p where (p.nom like concat('%',TRIM(:nom),'%') or p.description like concat('%',TRIM(:description),'%')) and p.id_categorie = :categorie and p.prix_actuel >= :min and p.prix_actuel <= :max")
                .setParameter("nom", motCle)
                .setParameter("description", motCle)
                .setParameter("categorie", id_categorie)
                .setParameter("min", min)
                .setParameter("max", max)
                .list();

		return produits;
	
	}

}
