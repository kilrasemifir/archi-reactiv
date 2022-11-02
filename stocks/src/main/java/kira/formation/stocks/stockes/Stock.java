package kira.formation.stocks.stockes;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
public class Stock {
    @Id
    private String id;
    private String productId;
    private int quantity;

    public Stock(String produitId, int quantite) {
        this.productId = produitId;
        this.quantity = quantite;
    }
}
