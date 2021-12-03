package com.okay.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        ResponseEntity responseEntity = super.translate(e);
        OAuth2Exception auth2Exception = (OAuth2Exception) responseEntity.getBody();
//        if (auth2Exception != null && e instanceof CustomAuthException) {
//            CustomAuthException customAuthException = (CustomAuthException) e;
//            auth2Exception.addAdditionalInformation("message", auth2Exception.getMessage());
//        } else if (auth2Exception != null && e instanceof MaintenanceException) {
//            MaintenanceException maintenanceException = (MaintenanceException) e;
//            auth2Exception.addAdditionalInformation("message", maintenanceException.getMessage());
//        }
//        if (auth2Exception != null && e instanceof TooManyRequestException) {
//            auth2Exception.addAdditionalInformation("captchaVisible", "true");
//            auth2Exception.addAdditionalInformation("message", auth2Exception.getMessage());
//            return new ResponseEntity<>(auth2Exception, responseEntity.getHeaders(), HttpStatus.TOO_MANY_REQUESTS);
//        }
        return new ResponseEntity<>(auth2Exception, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
}