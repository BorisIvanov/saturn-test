package saturn.common.service;

import org.springframework.amqp.core.Message;


public interface QueueConsumer {
    void setRabbitService(RabbitService rabbitService);
    void messageFromQueue(Message message);
}
