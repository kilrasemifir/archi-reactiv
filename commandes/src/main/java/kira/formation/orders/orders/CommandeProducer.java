package kira.formation.orders.orders;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommandeProducer {

    private final KafkaTemplate<String, Commande> kafkaTemplate;

    public CommandeProducer(KafkaTemplate<String, Commande> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Commande commande) {
        kafkaTemplate.send("orders", commande);
    }
}
