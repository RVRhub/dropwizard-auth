package com.test.auth;

import io.dropwizard.auth.Authorizer;

import java.security.Principal;

public class AuthAuthorizer implements Authorizer<Principal>
{
    @Override
    public boolean authorize(Principal user, String role)
    {
        return user.getName().equals(Constants.USER_NAME) && role.equals(Constants.ROLE_ADMIN);
    }
}