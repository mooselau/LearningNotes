package demo.rabbitmq;

import demo.model.User;

public interface RabbitMqService {
    public void sendMsg(String msg);

    public void sendUser(User user);
}
