package demo.rabbitmq;

import demo.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqServiceImpl implements RabbitMqService {

    @Value("spring-boot-queue-msg")
    private String msgRoutine;

    @Value("spring-boot-queue-user")
    private String userRouting;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMsg(String msg) {
        rabbitTemplate.convertAndSend(msgRoutine, msg);
    }

    @Override
    public void sendUser(User user) {
        rabbitTemplate.convertAndSend(userRouting, user);
    }
}
