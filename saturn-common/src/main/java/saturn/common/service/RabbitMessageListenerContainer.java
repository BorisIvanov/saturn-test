package saturn.common.service;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageListenerContainer extends SimpleMessageListenerContainer {

    @Autowired
    public RabbitMessageListenerContainer(
            Environment env,
            ConnectionFactory rabbitConnectionFactory,
            RabbitService rabbitService){
        setConnectionFactory(rabbitConnectionFactory);
        setQueueNames(env.getProperty("rabbit.queue.receive"));
        setMessageListener(rabbitService);
    }

}
