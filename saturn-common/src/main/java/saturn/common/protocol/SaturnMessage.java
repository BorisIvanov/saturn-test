package saturn.common.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.UUID;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AuthRequest.class, name = ProtocolCommand.LOGIN_CUSTOMER),
        @JsonSubTypes.Type(value = AuthResponse.class, name = ProtocolCommand.CUSTOMER_API_TOKEN),
        @JsonSubTypes.Type(value = CustomerError.class, name = ProtocolCommand.CUSTOMER_ERROR)
})
public class SaturnMessage<T> extends InnerSaturnMessage {
    public SaturnMessage() {
        sequenceId = UUID.randomUUID();
    }

    private String type;
    private T data;
    @JsonProperty("sequence_id")
    private UUID sequenceId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public UUID getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(UUID sequenceId) {
        this.sequenceId = sequenceId;
    }
}
