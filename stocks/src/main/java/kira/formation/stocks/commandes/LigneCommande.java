package kira.formation.stocks.commandes;

import lombok.Data;

@Data
public class LigneCommande {
    private String produitId;
    private int quantite;
}
