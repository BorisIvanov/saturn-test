package saturn.common.protocol;

public class AuthResponse extends SaturnMessage<AuthResponseData> {
    public AuthResponse(){
        setType(ProtocolCommand.CUSTOMER_API_TOKEN);
    }
}
