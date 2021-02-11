package demo.rabbitmq;

import demo.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageReceiver {

    @RabbitListener(queues = {"spring-boot-queue-msg"})
    public void receiveMsg(String msg) {
        System.out.println("===> message received: " + msg);
    }

    @RabbitListener(queues = {"spring-boot-queue-user"})
    public void receiveUser(User user) {
        System.out.println("===> message received: " + user);
    }

}
