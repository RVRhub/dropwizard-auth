package com.test.auth;


import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.security.Principal;


public class AuthAuthenticator implements Authenticator<BasicCredentials, Principal> {

        @Override
        public Optional<Principal> authenticate(BasicCredentials credentials) throws AuthenticationException
        {
            if (Constants.PASSWORD.equals(credentials.getPassword())) {
                return Optional.<Principal>of(new User(credentials.getUsername()));
            }
            return Optional.absent();
        }
}
