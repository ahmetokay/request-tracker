package com.okay.service.impl;

import com.okay.converter.UserConverter;
import com.okay.entity.User;
import com.okay.model.UserDto;
import com.okay.repository.UserRepository;
import com.okay.service.AuthService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    public AuthServiceImpl(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDto login(String username, String password) {
        User user = userRepository.findById(1L).get();
        kullaniciYetkilendirme(user);
        return userConverter.convertToDto(user);
    }

    private void kullaniciYetkilendirme(User user) {
        // authentication rolleri ayarlanıyor
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        user.getRoleList().stream().forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword(),
                        grantedAuthorities));
    }
}
