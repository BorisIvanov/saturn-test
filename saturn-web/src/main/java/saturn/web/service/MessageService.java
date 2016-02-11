package saturn.web.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saturn.common.protocol.SaturnMessage;
import saturn.common.service.JsonService;
import saturn.common.service.QueueConsumer;
import saturn.common.service.RabbitService;
import saturn.web.WebSocketServer;

import javax.websocket.Session;
import java.io.IOException;

@Service
public class MessageService implements QueueConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    private RabbitService rabbitService;
    private JsonService jsonService;

    @Autowired
    public MessageService(JsonService jsonService){
        this.jsonService = jsonService;
    }

    public void setRabbitService(RabbitService rabbitService){
        this.rabbitService = rabbitService;
    }

    @Override
    public void onMessage(Message message) {
        try {
            SaturnMessage saturnMessage = jsonService.readValue(message.getBody(), SaturnMessage.class);
            jsonService.writeAsOuterString(saturnMessage);
            WebSocketServer.send(saturnMessage.getSessionId(), jsonService.writeAsOuterString(saturnMessage));
        } catch(IOException e){
            logger.error("onMessage", e);
        }
    }

    public void send(Session session, String message) {
        try {
            SaturnMessage saturnMessage = jsonService.readValue(message, SaturnMessage.class);
            saturnMessage.setSessionId(session.getId());
            rabbitService.convertAndSend(saturnMessage);
        } catch(IOException e){
            logger.error("send", e);
        }
    }

}