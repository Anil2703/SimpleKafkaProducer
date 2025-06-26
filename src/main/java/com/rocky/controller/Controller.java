package com.rocky.controller;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    KafkaTemplate kafkaTemplate;
    
    @GetMapping("/hello")
    public String sayHello() {

        ProducerRecord<String, String> record1 = new ProducerRecord<>("test-topic","Key1", "Key1 Message-1");
        ProducerRecord<String, String> record2 = new ProducerRecord<>("test-topic","Key1", "Key1 Message-2");
        ProducerRecord<String, String> record3 = new ProducerRecord<>("test-topic","Key1", "Key1 Message-3");


        ProducerRecord<String, String> record4 = new ProducerRecord<>("test-topic","Key2", "Key1 Message-1");
        ProducerRecord<String, String> record5 = new ProducerRecord<>("test-topic","Key2", "Key1 Message-2");
        ProducerRecord<String, String> record6 = new ProducerRecord<>("test-topic","Key2", "Key1 Message-2");

        ProducerRecord<String, String> record7 = new ProducerRecord<>("test-topic","Key3", "Key1 Message-1");
        ProducerRecord<String, String> record8 = new ProducerRecord<>("test-topic","Key3", "Key1 Message-2");
        ProducerRecord<String, String> record9 = new ProducerRecord<>("test-topic","Key3", "Key1 Message-3");

        List<ProducerRecord> records = List.of(record1, record2, record3, record4);
        List<ProducerRecord> records2 = List.of(record5, record6, record7, record8, record9);

        records.forEach(record -> {
            record.headers().add("user-id", "rocky123".getBytes());
            record.headers().add("user-role", "admin".getBytes());
            record.headers().add("source", "producer-service".getBytes());
            kafkaTemplate.send(record);
        });

        records2.forEach(record -> {
            record.headers().add("user-id", "rocky123".getBytes());
            record.headers().add("user-role", "user".getBytes());
            record.headers().add("source", "producer-service".getBytes());
            kafkaTemplate.send(record);
        });


        return "Hello, World!";
    }
      
}
