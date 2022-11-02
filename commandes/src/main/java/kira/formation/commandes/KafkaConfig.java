package kira.formation.commandes;

import kira.formation.commandes.commandes.Commande;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

/**
 * Configuration de kafka en tant que producteur
 *
 * Ce service est utilisé pour pousser les orders dans un topic kafka orders que d'autres services pourront consommer.
 */
@Configuration
public class KafkaConfig {
    /**
     * Adresse du serveur kafka
     */
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    /**
     * Nom du topic kafka où les orders seront envoyées
     */

    @Value(value = "${kafka.topic}")
    private String topic;

    /**
     * Configuration de la connexion au serveur kafka
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    /**
     * Création du topic kafka orders
     */
    @Bean
    public NewTopic topic1() {
        return new NewTopic(topic, 1, (short) 1);
    }

    /**
     * Configuration du producteur kafka
     */
    @Bean
    public ProducerFactory<String, Commande> producerFactory() {
        // Création d'une map pour stocker les configurations
        Map<String, Object> configProps = new HashMap<>();
        // Adresse du serveur kafka
        configProps.put(
                BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        // Configuration de la sérialisation de la clé
        // Ici la clé sera une chaîne de caractères, nous utilisons donc la classe StringSerializer de kafka
        configProps.put(
                KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        // Configuration de la sérialisation de la valeur
        // La valeur sera uen order, nous utilisons donc la classe JsonSerializer de kafka
        configProps.put(
                VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /**
     * Création du template kafka.
     *
     * Un template est un design pattern qui permet de factoriser le code d'envoi de messages kafka.
     */
    @Bean
    public KafkaTemplate<String, Commande> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
