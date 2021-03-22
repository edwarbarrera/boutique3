package fr.greta.cda.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import fr.greta.cda.model.Lot;
import fr.greta.cda.model.Produit;
import fr.greta.cda.service.ProduitService;


@ManagedBean
@SessionScoped
public class ProduitBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value = "#{produitService}")
	private ProduitService produitService;
	private Produit selectedProduit;
	private String filtre ="tout";
	private Integer categorie = 0;
	private String recherche;
	private List<Lot> basket = new ArrayList();
	private double total;
	private double min=0;
	private double max=100;
	private List<Produit> produitList;
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public Integer getCategorie() {
		return categorie;
	}
	public void setCategorie(Integer categorie) {
		this.categorie = categorie;
	}
	public Produit getSelectedProduit() {
		return selectedProduit;
	}
	public void setSelectedProduit(Produit selectedProduit) {
		this.selectedProduit = selectedProduit;
	}
	public int getBasketSize() {
			int quantity = 0;
			for( Lot lot : basket ) quantity += lot.getQuantity();
			return quantity;
	}
	
	public List<Produit> getProduitList() {
		switch (filtre) {
		default : produitList = produitService.getAllProduit(min, max);
			System.out.println("Tous les porduits entre " +min + "et " + max);
			break;
		case "motCle" : produitList = produitService.findByMotCle(recherche, min, max);
			System.out.println("le mot cle recherché est : " + recherche + " min = " + min + " max = " + max );
			break;
		case "categorie" : produitList = produitService.findByCategorie(categorie, min, max);
			System.out.println("la categorie recherchee est : " + categorie + " min = " + min + " max = " + max );
			break;
		case "motCleCategorie" : produitList = produitService.findByMotCLeCategorie(recherche, categorie, min, max);
			System.out.println("mot cle = " +recherche + " categorie = " + categorie + " min = "  + min + " max = " + max);
			break;
	}
		return produitList;	
	}
	
	public String filtrer() {
		System.out.println(filtre);
		if (getRecherche().isEmpty() && getCategorie() == 0) {
			filtre = "tout"; 
		}
		else if (!getRecherche().isEmpty() && getCategorie() == 0 ){
			filtre = "motCle";
		}
		else if (getRecherche().isEmpty() && getCategorie() != 0 ){
			filtre = "categorie";
		}
		else if (!getRecherche().isEmpty() && getCategorie() != 0 ){
			filtre = "motCleCategorie";
		}
		System.out.println("recherche par " + filtre);
		System.out.println("critères de recherche : mot cle = " + recherche + ", cat= "+ categorie + ", prix = " + min + " " + max);
		return filtre;
	}
	
	public void processAddAction( ActionEvent event) {
		for( Lot batch : basket ) {
			if (batch.getProduit().getQuantite() <= batch.getQuantity()) {
				System.out.println("Quantité max atteinte");
				return;
				}
			else if ( batch.getProduit().getId_produit() == getSelectedProduit().getId_produit() ) {
				batch.addOne();
				System.out.println("Produit deja dans le panier, quantite ++");
				total += getSelectedProduit().getPrix_actuel();
				System.out.println(total);
				return;
			}
		}
		basket.add( new Lot( getSelectedProduit(), 1));
		System.out.println("Produit absent du panier, ajouté");
		total += getSelectedProduit().getPrix_actuel();
		System.out.println(total);
		System.out.println(basket.toString());
	}
	public void refreshPanier() {
		total = 0;
		for (int i=0 ; i < basket.size(); i ++) {
			if (basket.get(i).getQuantity() == 0) {
				basket.remove(i);
			}
			total += basket.get(i).getProduit().getPrix_actuel()*basket.get(i).getQuantity();
		}	
	}
	public int getProduitListSize() {
		return produitList.size();
	}
	public void setProduitService(ProduitService produitService) {
		this.produitService = produitService;
	}
	public void setProduitList(List<Produit> produitList) {
		this.produitList = produitList;
	}
	public String getRecherche() {
		return recherche;
	}
	public void setRecherche(String recherche) {
		this.recherche = recherche;
	}
	public List<Lot> getBasket() {
		return basket;
	}
	public double getTotal() {
		return  Math.round(total * 100.0) / 100.0;
	}
}
