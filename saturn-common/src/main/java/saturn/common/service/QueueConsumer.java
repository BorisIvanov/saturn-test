package saturn.common.service;

import org.springframework.amqp.core.Message;

public interface QueueConsumer {
    void onMessage(Message message);
}
