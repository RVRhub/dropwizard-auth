package com.test.auth;


import com.codahale.metrics.MetricRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.jersey.DropwizardResourceConfig;
import io.dropwizard.logging.BootstrapLogging;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.glassfish.jersey.servlet.ServletProperties;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import java.security.Principal;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by roman on 30.04.16.
 */
public class AuthTest extends JerseyTest
{
    protected static final String BASIC_PREFIX = "Basic";
    protected static final String ORDINARY_USER = "Roman";
    protected static final String ORDINARY_USER_ENCODED_TOKEN = "Um9tYW46cGFzcw";

    private static final String TRANSFER_ENCODING = "Transfer-Encoding";
    private static final String CHUNKED = "chunked";
    private static final ObjectMapper JSON_MAPPER = Jackson.newObjectMapper();

    static {
        BootstrapLogging.bootstrap();
    }

    public static class BasicAuthTestResourceConfig extends DropwizardResourceConfig {
        public BasicAuthTestResourceConfig()
        {
            super(true, new MetricRegistry());
            register(AuthResource.class);
            register(RolesAllowedDynamicFeature.class);

            register(new AuthDynamicFeature(
                    new BasicCredentialAuthFilter.Builder<Principal>()
                            .setAuthenticator(new AuthAuthenticator())
                            .setAuthorizer(new AuthAuthorizer())
                            .setRealm("SUPER SECRET STUFF")
                            .buildAuthFilter()));
        }

    }

    @Override
    protected DeploymentContext configureDeployment() {
        forceSet(TestProperties.CONTAINER_PORT, "0");
        return ServletDeploymentContext.builder(new BasicAuthTestResourceConfig())
                .initParam(ServletProperties.JAXRS_APPLICATION_CLASS, BasicAuthTestResourceConfig.class.getName())
                .build();
    }


    @Test
    public void resourceWithoutAuth200()
    {
        assertThat(target("/test/noauth").request()
                .get(String.class))
                .isEqualTo("hello");

    }


//    @Test
//    public void resourceWithAuthenticationWithoutAuthorizationWithCorrectCredentials200() {
//        assertThat(target("/test/admin").request()
//                .header(HttpHeaders.AUTHORIZATION, BASIC_PREFIX + " " + ORDINARY_USER_ENCODED_TOKEN)
//                .get(String.class))
//                .isEqualTo("'" + ORDINARY_USER + "' has user privileges");
//    }

}
