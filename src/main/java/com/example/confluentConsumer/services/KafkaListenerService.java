package com.example.confluentConsumer.services;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaListenerService {

    @Value("${spring.kafka.topic.input}")
    String topicName;


    @KafkaListener(id = "consumer-1", topics = "names-topic",
          groupId = "default-group")
    public void listen(ConsumerRecord<String,String> data, Acknowledgment acknowledgment) {

        try{
            System.out.println(String.format("consumed record : %s with key %s",data.value(),data.key()));
        acknowledgment.acknowledge();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
