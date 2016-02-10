package saturn.common.protocol;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.util.UUID;


public class AuthResponseData {
    @JsonProperty("api_token")
    private UUID apiToken;
    @JsonProperty("api_token_expiration_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private DateTime apiTokenExpirationDate;

    public UUID getApiToken() {
        return apiToken;
    }

    public void setApiToken(UUID apiToken) {
        this.apiToken = apiToken;
    }

    public DateTime getApiTokenExpirationDate() {
        return apiTokenExpirationDate;
    }

    public void setApiTokenExpirationDate(DateTime apiTokenExpirationDate) {
        this.apiTokenExpirationDate = apiTokenExpirationDate;
    }
}
