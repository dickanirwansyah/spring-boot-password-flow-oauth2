package com.spring.oauth2.springdemooauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2ServerConfiguration {

    //resource-server
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{

        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        @Override
        public void configure(final ResourceServerSecurityConfigurer resource) throws Exception{
            resource.tokenStore(new JwtTokenStore(jwtAccessTokenConverter));
        }

        @Override
        public void configure(HttpSecurity httpSecurity) throws Exception{
            httpSecurity
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();
        }
    }

    //authorization-server
    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter{

        @Autowired
        private JwtAccessTokenConverter jwtAccessTokenConverter;

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
            endpoints
                    .tokenStore(new JwtTokenStore(jwtAccessTokenConverter))
                    .authenticationManager(authenticationManager)
                    .accessTokenConverter(jwtAccessTokenConverter);
        }

        @Override
        public void configure(final ClientDetailsServiceConfigurer clients) throws Exception{
            clients
                    .inMemory()
                    .withClient("client")
                    .secret(passwordEncoder.encode("secret"))
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("read", "write");
        }
    }
}
