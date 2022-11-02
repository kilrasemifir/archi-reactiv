package kira.formation.stocks;

import kira.formation.stocks.commandes.Commande;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;


/**
 * Configuration de Kafka pour la création d'un consumer
 */
@EnableKafka
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
     * Configuration d'un consumer kafka
     */
    @Bean
    public ConsumerFactory<String, Commande> consumerFactory() {

        JsonDeserializer<Commande> deserializer = new JsonDeserializer<>(Commande.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(GROUP_ID_CONFIG, "group_id");
        configProps.put(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
        return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Commande> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Commande> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
