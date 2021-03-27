package com.example.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails(clientDetails());
//        clients.inMemory().withClient("test_client").secret(passwordEncoder()
        clients.inMemory().withClient("test_client").secret(passwordEncoder().encode("123456")).redirectUris("https://www.baidu.com")
                .authorizedGrantTypes("authorization_code","client_credentials").scopes("all")
                .accessTokenValiditySeconds(800).refreshTokenValiditySeconds(600);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
//        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
//                .userDetailsService(userDetailsService())
//                .authenticationManager(authenticationManagerBean());
                .authenticationManager(authenticationManager);
        endpoints.tokenServices(defaultTokenServices);
//        endpoints.tokenServices(defaultTokenServices());
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //允许check_token访问
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("permitAll()");
//        security.checkTokenAccess("isAuthenticated()");
        //允许表单的认证
        security.allowFormAuthenticationForClients();
    }



    @Bean
    public ClientDetailsService clientDetails() {

        InMemoryClientDetailsService inMemoryClientDetailsService = new InMemoryClientDetailsService();

        Map<String, ClientDetails> clientDetailsStore = new HashMap<>();
        clientDetailsStore.put("test_client", new BaseClientDetails("test_client", "",
                "all", "password, refresh_token", "ROLE_CLIENT, ROLE_TRUSTED_CLIENT"));

        inMemoryClientDetailsService.setClientDetailsStore(clientDetailsStore);


        return inMemoryClientDetailsService;
    }



    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
//        return new NoOpPasswordEncoder();
    }

    /*@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // provides the default AuthenticationManager as a Bean
        return null;
    }*/

    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encode);
    }

}
