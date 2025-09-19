package ru.javabegin.micro.demo.backend.configs;

import com.example.avro.UserCreatedEvent;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.*;

// Проверка создания топиков:
// docker exec -i spring-kafka-avro-demo-kafka-1 kafka-topics --list --bootstrap-server kafka:9092

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, UserCreatedEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
        configProps.put("schema.registry.url", "http://localhost:8081"); // Было: localhost:8081
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, UserCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}


