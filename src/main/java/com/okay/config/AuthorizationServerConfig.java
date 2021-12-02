package com.okay.config;

import com.okay.service.CustomUserDetailService;
import com.okay.service.UserService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.oauth2.config.annotation.configuration.ClientDetailsServiceConfiguration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.Arrays;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
@EnableConfigurationProperties(value = SecurityProperties.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private TokenStore tokenStore;
    private final DataSource dataSource;
    private final SecurityProperties securityProperties;
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    private final CustomUserDetailService userDetailService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthorizationServerConfig(
            DataSource dataSource,
            SecurityProperties securityProperties,
            CustomUserDetailService userDetailService,
            AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.securityProperties = securityProperties;
        this.dataSource = dataSource;
        this.userDetailService = userDetailService;
        this.userService = userService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    @Bean
    public ClientDetailsService clientDetailsService() throws Exception {
        ClientDetailsServiceConfiguration serviceConfig = new ClientDetailsServiceConfiguration();
        serviceConfig.clientDetailsServiceConfigurer().jdbc(dataSource);
        return serviceConfig.clientDetailsService();
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        TokenEnhancerChain chain = new TokenEnhancerChain();
//        chain.setTokenEnhancers(List.of(tokenEnhancer(), jwtAccessTokenConverter()));
        endpoints.userDetailsService(userDetailService)
//                .tokenServices(tokenServices())
//                .tokenEnhancer(chain)
//                .authenticationManager(authenticationManager)
                .exceptionTranslator(new CustomResponseExceptionTranslator());
//        .tokenEnhancer(chain)
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Bean
    public TokenStore tokenStore() {
        if (tokenStore == null) {
            tokenStore = new JdbcTokenStore(dataSource);
        }
        return tokenStore;
    }

    @Primary
    @Bean
    public DefaultTokenServices tokenServices() throws Exception {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        TokenEnhancerChain chain = new TokenEnhancerChain();
//        chain.setTokenEnhancers(List.of(tokenEnhancer(), jwtAccessTokenConverter()));
//        tokenServices.setTokenEnhancer(chain);
        tokenServices.setReuseRefreshToken(false);
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(this.tokenStore());
        tokenServices.setClientDetailsService(clientDetailsService());
        return tokenServices;
    }

}