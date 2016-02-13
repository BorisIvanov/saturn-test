package saturn.common.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import saturn.common.protocol.AuthRequest;

import static org.junit.Assert.assertTrue;


public class JsonServiceTest {

    @Test
    public void sessionIdHide() throws JsonProcessingException {
        JsonService jsonService = new JsonService();
        AuthRequest authRequest = new AuthRequest();
        assertTrue(jsonService.writeAsOuterString(authRequest).indexOf("sessionId") == -1);
        assertTrue(new String(jsonService.writeValueAsBytes(authRequest)).indexOf("sessionId") > -1);
    }

}
