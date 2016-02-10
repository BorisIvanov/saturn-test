package com.websocket;

import com.websocket.config.RedisHttpSessionConfig;
import com.websocket.config.WebConfig;
import org.junit.runner.RunWith;
import org.springframework.util.SocketUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {WebConfig.class, RedisHttpSessionConfig.class, WebSocketConfig.class})
public class AuthSocketTest {/*
    @BeforeClass
    public static void setup() throws Exception {

        // Since test classpath includes both embedded Tomcat and Jetty we need to
        // set a Spring profile explicitly to bypass WebSocket engine detection.
        // See {@link org.springframework.samples.portfolio.config.WebSocketConfig}

        // This test is not supported with Jetty because it doesn't seem to support
        // deployment withspecific ServletContainerInitializer's at for testing

        System.setProperty("spring.profiles.active", "test.tomcat");

        port = SocketUtils.findAvailableTcpPort();

        server = new org.springframework.samples.portfolio.web.support.TomcatWebSocketTestServer(port);
        server.deployConfig(TestDispatcherServletInitializer.class, WebSecurityInitializer.class);
        server.start();

        loginAndSaveJsessionIdCookie("fabrice", "fab123", headers);

        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        RestTemplateXhrTransport xhrTransport = new RestTemplateXhrTransport(new RestTemplate());
        xhrTransport.setRequestHeaders(headers);
        transports.add(xhrTransport);

        sockJsClient = new SockJsClient(transports);
    }
*/
}
