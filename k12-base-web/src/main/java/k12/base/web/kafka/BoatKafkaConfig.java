package k12.base.web.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.Map;

/**
 * 因为创建非默认的 Consumer Group 会与自动配置冲突，
 * 所以此配置类中手动提供的 kafkaListenerContainerFactory & consumerFactory Bean 用于抑制自动配置
 */
@Configuration
@ConditionalOnClass(KafkaTemplate.class)
@EnableConfigurationProperties(KafkaProperties.class)
public class BoatKafkaConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory () {
        Map<String, Object> consumerConfigs = kafkaProperties.buildConsumerProperties();
        return new DefaultKafkaConsumerFactory<>(consumerConfigs);
    }

}
