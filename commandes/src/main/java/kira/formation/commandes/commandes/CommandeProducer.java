package kira.formation.commandes.commandes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CommandeProducer {

    @Value(value = "${kafka.topic}")
    private String topic;

    private final KafkaTemplate<String, Commande> kafkaTemplate;

    public CommandeProducer(KafkaTemplate<String, Commande> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(Commande commande) {
        kafkaTemplate.send(topic, commande);
    }
}
