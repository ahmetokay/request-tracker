package com.okay.config;

import com.okay.constant.ClientConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private DefaultTokenServices tokenServices;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = getJwtFromRequest(request);
        if (bearerToken != null) {
            try {
                OAuth2AccessToken accessToken = tokenServices.readAccessToken(bearerToken);
                if (accessToken != null && accessToken.getExpiration().after(new Date())) {
                    OAuth2Authentication oAuth2Authentication = tokenServices.loadAuthentication(bearerToken);
                    OAuth2Request oAuth2Request = oAuth2Authentication.getOAuth2Request();
                    String clientId = oAuth2Request.getClientId();
                    if (clientId != null && clientId.equals(ClientConstant.IpTvClient.CLIENT_ID)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                                = (UsernamePasswordAuthenticationToken) oAuth2Authentication.getUserAuthentication();
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            } catch (Exception ex) {
                log.warn("Invalid authentication.", ex);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String HEADER = "Authorization";
        String authorization = request.getHeader(HEADER);
        String TOKEN_PREFIX = "Bearer";
        if (StringUtils.hasText(authorization) && authorization.startsWith(TOKEN_PREFIX)) {
            return authorization.substring(7);
        }
        return null;
    }
}