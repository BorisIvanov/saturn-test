package saturn.common.protocol;

public class AuthRequest extends SaturnMessage<AuthRequestData> {
    public AuthRequest(){
        setType(ProtocolCommand.LOGIN_CUSTOMER);
        setData(new AuthRequestData());
    }
}
