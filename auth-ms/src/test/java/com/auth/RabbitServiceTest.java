package com.auth;

import com.auth.config.AppConfig;
import com.auth.config.DbConfig;
import com.auth.config.RabbitConfig;
import com.auth.domain.Account;
import com.auth.protocol.AuthRequest;
import com.auth.protocol.AuthResponse;
import com.auth.protocol.CustomerError;
import com.auth.repository.AccountRepository;
import com.auth.service.JsonService;
import com.auth.service.RabbitService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;


import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, RabbitConfig.class, DbConfig.class})
public class RabbitServiceTest {

    @Autowired
    RabbitService rabbitService;
    @Autowired
    JsonService jsonService;
    @Autowired
    AccountRepository accountRepository;
    private boolean before = false;

    public String getStringFromResource(String path) throws IOException {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = inputStream.read();
        while (reads != -1) {
            baos.write(reads);
            reads = inputStream.read();
        }
        return new String(baos.toByteArray()).replace("\r\n", "");
    }

    @Before
    public void setUp() {
        if (accountRepository.find("AAA", "AAA") != null) {
            return;
        }

        Account account = new Account();
        account.setName("AAA");
        account.setPassword("AAA");
        account.setToken(UUID.randomUUID());
        account.setTokenExpire(DateTime.now(DateTimeZone.UTC).plusDays(30));
        accountRepository.add(account);
    }

    @Test
    public void testFail() throws Exception {
        AuthRequest request = new AuthRequest();
        request.getData().setEmail("AAA1");
        request.getData().setPassword("AAA1");
        rabbitService.convertAndSend("node-0-out", request);
        Message message = null;
        while (message == null) {
            message = rabbitService.receive("node-0-in");
        }
        CustomerError error = jsonService.readValue(message.getBody(), CustomerError.class);
        assertEquals(error.getSequenceId(), request.getSequenceId());
    }

    @Test
    public void testSuccess() throws Exception {
        AuthRequest request = new AuthRequest();
        request.getData().setEmail("AAA");
        request.getData().setPassword("AAA");

        rabbitService.convertAndSend("node-0-out", request);
        Message message = null;
        while (message == null) {
            message = rabbitService.receive("node-0-in");
        }
        AuthResponse authResponse = jsonService.readValue(message.getBody(), AuthResponse.class);
        assertEquals(authResponse.getSequenceId(), request.getSequenceId());
    }

}
