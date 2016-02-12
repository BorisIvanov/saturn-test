package saturn.auth.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import saturn.auth.config.AppConfig;
import saturn.auth.config.DbConfig;
import saturn.auth.config.DbInit;
import saturn.common.protocol.AuthRequest;
import saturn.common.protocol.AuthResponse;
import saturn.common.protocol.CustomerError;
import saturn.common.service.JsonService;
import saturn.common.service.RabbitService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, DbConfig.class})
public class AuthServiceIntTest {

    @Autowired
    RabbitService rabbitService;
    @Autowired
    JsonService jsonService;
    @Autowired
    private AmqpAdmin amqpAdmin;


    @Before
    public void setUp() {
        amqpAdmin.deleteQueue("node-0-out");
        amqpAdmin.deleteQueue("node-0-in");
        amqpAdmin.declareQueue(new Queue("node-0-out"));
        amqpAdmin.declareQueue(new Queue("node-0-in"));
    }

    @Test
    public void testFail() throws Exception {
        AuthRequest request = new AuthRequest();
        request.getData().setEmail("AAA1");
        request.getData().setPassword("AAA1");
        request.setSessionId("0");
        rabbitService.convertAndSend("node-0-out", request);
        Message message = null;
        while (message == null) {
            message = rabbitService.receive("node-0-in");
        }
        CustomerError error = jsonService.readValue(message.getBody(), CustomerError.class);
        assertEquals(error.getSequenceId(), request.getSequenceId());
        assertEquals(error.getSessionId(), request.getSessionId());
    }

    @Test
    public void testSuccess() throws Exception {
        AuthRequest request = new AuthRequest();
        request.getData().setEmail(DbInit.DEFAULT_USER_NAME);
        request.getData().setPassword(DbInit.DEFAULT_USER_PASSWORD);
        request.setSessionId("0");

        rabbitService.convertAndSend("node-0-out", request);
        Message message = null;
        while (message == null) {
            message = rabbitService.receive("node-0-in");
        }
        AuthResponse authResponse = jsonService.readValue(message.getBody(), AuthResponse.class);
        assertEquals(authResponse.getSequenceId(), request.getSequenceId());
        assertEquals(authResponse.getSessionId(), request.getSessionId());
    }

}
