package saturn.common.protocol;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("sessionIdHide")
public class InnerSaturnMessage {
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
