package com.example.icesitrade.security;

import java.util.stream.Collectors;
import com.example.icesitrade.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRole().getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName())) // Esto da List<GrantedAuthority>
                .collect(Collectors.toList());

    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); // Spring usa el "username", que en nuestro caso es el email
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Cambia esto si piensas implementar lógica de expiración
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Cambia esto si piensas implementar bloqueo de cuenta
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Cambia esto si piensas implementar expiración de credenciales
    }

    @Override
    public boolean isEnabled() {
        return true; // Cambia esto si tienes usuarios deshabilitados
    }
}
