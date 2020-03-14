package demo.configs;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties("demo.rabbitmq.queue")
public class RabbitMqConfig {

    @Value("spring-boot-queue-msg")
    private String msgQueueName;
    @Value("spring-boot-queue-user")
    private String userQueueName;

    @Bean
    public Queue createQueueMsg() {
        return new Queue(msgQueueName, true);
    }

    @Bean
    public Queue createQueueUser() {
        return new Queue(userQueueName, true);
    }

}
