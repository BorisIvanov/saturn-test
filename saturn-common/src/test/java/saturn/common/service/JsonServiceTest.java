package saturn.common.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.joda.time.DateTime;
import org.junit.Test;
import saturn.common.protocol.AuthRequest;
import saturn.common.protocol.AuthResponse;
import saturn.common.protocol.AuthResponseData;
import saturn.common.protocol.CustomerError;
import saturn.common.protocol.SaturnMessage;

import java.io.IOException;

import static org.junit.Assert.assertTrue;


public class JsonServiceTest {

    @Test
    public void sessionIdHide() throws JsonProcessingException {
        JsonService jsonService = new JsonService();
        AuthRequest authRequest = new AuthRequest();
        assertTrue(!jsonService.writeAsOuterString(authRequest).contains("sessionId"));
        assertTrue(new String(jsonService.writeValueAsBytes(authRequest)).contains("sessionId"));
    }


    @Test
    public void deserializeCommandFromClient() throws IOException {
        JsonService jsonService = new JsonService();

        boolean fail = false;
        try {
            String failCommand = "{\"type\":\"FAIL_COMMAND_ID\",\"data\":{\"email\":null,\"password\":null},\"sequence_id\":\"40af1120-6244-42cb-9650-ad860592f0ba\"}";
            jsonService.readValue(failCommand, SaturnMessage.class);
        } catch (JsonMappingException e) {
            fail = true;
        }
        assertTrue(fail);

        fail = false;
        try {
            String failCommand = "{\"type1\":\"FAIL_COMMAND_ID\"}";
            jsonService.readValue(failCommand, SaturnMessage.class);
        } catch (JsonMappingException e) {
            fail = true;
        }
        assertTrue(fail);

        fail = false;
        try {
            String failCommand = "FAIL_COMMAND_ID";
            jsonService.readValue(failCommand, SaturnMessage.class);
        } catch (JsonParseException e) {
            fail = true;
        }
        assertTrue(fail);

        String successCommand = "{\"type\":\"LOGIN_CUSTOMER\",\"data\":{\"email\":null,\"password\":null},\"sequence_id\":\"40af1120-6244-42cb-9650-ad860592f0ba\"}";
        SaturnMessage result = jsonService.readValue(successCommand, SaturnMessage.class);
        assertTrue(result.getClass() == AuthRequest.class);
    }


    @Test
    public void serializeCustomerError() throws JsonProcessingException {
        JsonService jsonService = new JsonService();
        CustomerError error = new CustomerError();
        int pos = jsonService.writeAsOuterString(error).indexOf("type");
        assertTrue(jsonService.writeAsOuterString(error).indexOf("type", pos + 1) == -1);
    }

    @Test
    public void serializeAuthResponse() throws JsonProcessingException {
        JsonService jsonService = new JsonService();
        AuthResponse response = new AuthResponse();
        response.setData(new AuthResponseData());
        response.getData().setApiTokenExpirationDate(DateTime.now());
        System.out.println(jsonService.writeAsOuterString(response));
    }
}
