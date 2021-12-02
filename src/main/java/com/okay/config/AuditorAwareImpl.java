package com.okay.config;

import com.okay.model.UserDto;
import com.okay.service.UserService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<Long> {

    private UserService userService;

    @Override
    public Optional<Long> getCurrentAuditor() {

        if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null) {
            //if create by system
            return Optional.of(-1L);
        } else {
            String username;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }

            if (NumberUtils.isParsable(username)) {
                UserDto user = userService.findByUsername(username);
                return Optional.of(user != null ? user.getId() : -1);
            } else {
                return Optional.of(-1L);
            }
        }
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
