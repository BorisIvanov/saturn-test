package com.auth.protocol;

public class AuthRequest extends SaturnMessage<AuthRequestData> {
    public AuthRequest(){
        setType("LOGIN_CUSTOMER");
        setData(new AuthRequestData());
    }
}
