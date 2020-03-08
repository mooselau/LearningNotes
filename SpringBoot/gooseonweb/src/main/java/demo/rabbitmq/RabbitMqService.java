package demo.rabbitmq;

import demo.aop.User;

public interface RabbitMqService {
    public void sendMsg(String msg);

    public void sendUser(User user);
}
