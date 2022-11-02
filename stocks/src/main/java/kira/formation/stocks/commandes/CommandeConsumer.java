package kira.formation.stocks.commandes;

import kira.formation.stocks.stockes.Stock;
import kira.formation.stocks.stockes.StockService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CommandeConsumer {

    private final StockService stockService;

    public CommandeConsumer(StockService stockService) {
        this.stockService = stockService;
    }

    @KafkaListener(topics = "commandes", groupId = "group_id")
    public void listen(@Payload Commande commande) {
        System.out.println("Received Message in group group_id: " + commande);
        if (commande != null && commande.getLignes() != null) {
            commande.getLignes().forEach(ligneCommande -> {
                stockService.saveStock(new Stock(ligneCommande.getProduitId(), -ligneCommande.getQuantite()));
            });
        }
    }
}
