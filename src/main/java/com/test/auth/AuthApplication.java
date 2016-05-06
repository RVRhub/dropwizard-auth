package com.test.auth;

import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.security.Principal;


public class AuthApplication extends Application<AuthConfiguration> {
    public static void main(String[] args) throws Exception {
        new AuthApplication().run(args);
    }

    @Override
    public String getName() {
        return "dw";
    }

    @Override
    public void initialize(Bootstrap<AuthConfiguration> bootstrap) {
        //bootstrap.setName("article");
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(AuthConfiguration configuration,
                    Environment environment) {
        final UserResource resource = new UserResource( );

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<>()
                        .setAuthenticator(new AuthAuthenticator())
                        .setAuthorizer(new AuthAuthorizer())
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));


        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Principal.class));
        //environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(resource);

    }
}