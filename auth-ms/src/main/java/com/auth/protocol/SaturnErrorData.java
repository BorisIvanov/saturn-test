package com.auth.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaturnErrorData {
    @JsonProperty("error_code")
    private String code;
    @JsonProperty("error_description")
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
