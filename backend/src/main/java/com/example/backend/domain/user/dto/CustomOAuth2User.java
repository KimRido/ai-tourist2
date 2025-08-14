package com.example.backend.domain.user.dto;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

//1.LoginFilter
//2.UserService
//3.LoginSuccessHandler

//1.UserService
//2.SocialSuccessHandler
//3.CustomOAuth2User
public class CustomOAuth2User implements OAuth2User {

    private final Map<String, Object> attributes;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String username;

    public CustomOAuth2User(Map<String, Object> attributes,
                            Collection<? extends GrantedAuthority> authorities,
                            String username) {
        this.attributes = attributes;
        this.authorities = authorities;
        this.username = username;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getName() {
        return username;
    }
}
