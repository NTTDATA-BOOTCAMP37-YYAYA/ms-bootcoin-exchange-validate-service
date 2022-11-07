package com.nttdata.bootcamp.msbootcoinexchangevalidate.infrastructure.consumer.adapter.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.BankTransaction;
import com.nttdata.bootcamp.msbootcoinexchangevalidate.domain.model.Exchange;


@Configuration
public class KafkaConsumerConfig {

  @Value("${spring.kafka.bootstrap-servers}")
  private String bootstrapServers;
  
  @Bean(name="consumerFactoryBankTransaction")
  public ConsumerFactory<String, BankTransaction> consumerFactory() {
    JsonDeserializer<BankTransaction> jsonDeserializer = new JsonDeserializer<>(BankTransaction.class, false);
    jsonDeserializer.addTrustedPackages("*");
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), jsonDeserializer);
  }
  
  @Bean(name="kafkaListenerContainerFactoryBankTransaction")
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, BankTransaction>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, BankTransaction> factory = new
    ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
  
  
  
  @Bean(name="consumerFactoryExchange")
  public ConsumerFactory<String, Exchange> consumerFactoryExchange() {
    JsonDeserializer<Exchange> jsonDeserializer = new JsonDeserializer<>(Exchange.class, false);
    jsonDeserializer.addTrustedPackages("*");
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), jsonDeserializer);
  }
  
  @Bean(name="kafkaListenerContainerFactoryExchange")
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Exchange>> kafkaListenerContainerFactoryExchange() {
    ConcurrentKafkaListenerContainerFactory<String, Exchange> factory = new
    ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactoryExchange());
    return factory;
  }
  
  @Bean
  public Map<String, Object> consumerConfigs() {
  Map<String, Object> props = new HashMap<>();
  props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
  bootstrapServers);
  props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
  StringDeserializer.class);
  props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
  props.put(ConsumerConfig.GROUP_ID_CONFIG, "myGroup");
  return props;
  }


    
  


}
