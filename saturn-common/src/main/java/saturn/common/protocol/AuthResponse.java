package saturn.common.protocol;

public class AuthResponse extends SaturnMessage<AuthResponseData> {
    public AuthResponse(){
        setType("CUSTOMER_API_TOKEN");
    }
}