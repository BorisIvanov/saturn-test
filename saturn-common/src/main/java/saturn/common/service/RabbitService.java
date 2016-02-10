package saturn.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService extends RabbitTemplate {
    private static final Logger logger = LoggerFactory.getLogger(RabbitService.class);

    @Autowired
    private QueueConsumer queueConsumer;

    private String queueForSend;

    @Autowired
    public RabbitService(ConnectionFactory connectionFactory, String queueForSend) {
        super(connectionFactory);
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setJsonObjectMapper(new JsonService());
        setMessageConverter(converter);
        this.queueForSend = queueForSend;
    }

    @Override
    public void onMessage(Message message) {
        queueConsumer.onMessage(message);
        /*
        UUID sequenceId;
        AuthRequest authRequest;

        try {
            authRequest = jsonService.readValue(message.getBody(), AuthRequest.class);
            sequenceId = authRequest.getSequenceId();
        } catch (Exception e){
            logger.error("onMessage", e);
            return;
        }

        try{
            AuthResponse result = new AuthResponse();
            result.setSequenceId(sequenceId);
            result.setData(authService.auth(authRequest.getData().getEmail(), authRequest.getData().getPassword()));
            convertAndSend(queueForSend, result);
        } catch(Exception e){
            logger.error("onMessage", e);
            CustomerError customerError = new CustomerError();
            customerError.setSequenceId(sequenceId);
            convertAndSend(queueForSend, customerError);
        }*/
    }

    @Override
    public void convertAndSend(String routingKey, Object message) {
        super.convertAndSend(routingKey, message, messagePostProcessor -> {
            messagePostProcessor.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
            return messagePostProcessor;
        });
    }

}
