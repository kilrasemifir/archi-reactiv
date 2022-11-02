package kira.formation.commandes.commandes.valueobjects;

/**
 * Représente une ligne d'une commande
 */
public class LigneCommande {

    private String produitId;
    private int quantite;

    public String getProduitId() {
        return produitId;
    }

    public void setProduitId(String produitId) {
        this.produitId = produitId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "LigneCommande [produitId=" + produitId + ", quantite=" + quantite + "]";
    }
}
