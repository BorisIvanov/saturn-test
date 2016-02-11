package saturn.common.protocol;

import com.fasterxml.jackson.annotation.JsonFilter;

public class InnerSaturnMessage {
    @JsonFilter("InnerSaturnMessageFilter")
    private String sessionId;
    @JsonFilter("InnerSaturnMessageFilter")
    private String nodeId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}
