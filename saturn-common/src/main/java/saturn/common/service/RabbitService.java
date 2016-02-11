package saturn.common.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Service
public class RabbitService extends RabbitTemplate {

    private QueueConsumer queueConsumer;
    private String queueForSend;

    @Autowired
    public RabbitService(ConnectionFactory connectionFactory,
                         QueueConsumer queueConsumer,
                         Environment env) {
        super(connectionFactory);
        this.queueConsumer = queueConsumer;
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setJsonObjectMapper(new JsonService());
        setMessageConverter(converter);
        this.queueForSend = env.getProperty("rabbit.queue.send");
        queueConsumer.setRabbitService(this);
    }

    @Override
    public void onMessage(Message message) {
        queueConsumer.onMessage(message);
    }

    @Override
    public void convertAndSend(String routingKey, Object message) {
        super.convertAndSend(routingKey, message, messagePostProcessor -> {
            messagePostProcessor.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
            return messagePostProcessor;
        });
    }

    @Override
    public void convertAndSend(Object message) {
        super.convertAndSend(this.queueForSend, message, messagePostProcessor -> {
            messagePostProcessor.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
            return messagePostProcessor;
        });
    }

}
