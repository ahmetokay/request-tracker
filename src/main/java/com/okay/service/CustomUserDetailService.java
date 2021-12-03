package com.okay.service;

import com.okay.config.UserPrincipal;
import com.okay.model.RoleDto;
import com.okay.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Primary
@Transactional
@Component
public class CustomUserDetailService implements UserDetailsService {

    private final UserService userService;
    public static Collection<? extends GrantedAuthority> grantedAuthorities;

    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("usernameNotFound");
        }

        grantedAuthorities = getAuthorities(user.getRoleList());
        return new UserPrincipal(user, grantedAuthorities);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<RoleDto> roles) {
        return getGrantedAuthority(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<RoleDto> roles) {
        List<String> privileges = new ArrayList<>();

        for (RoleDto role : roles) {
            privileges.add(role.getName());
        }

        return privileges;

    }

    private List<GrantedAuthority> getGrantedAuthority(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }

        return authorities;
    }
}