package com.auth.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class SaturnMessage<T> {
    public SaturnMessage(){
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
