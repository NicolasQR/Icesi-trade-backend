package com.example.icesitrade.security;

import com.example.icesitrade.model.User;
import com.example.icesitrade.model.Permission;
import com.example.icesitrade.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscar el usuario por su email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Obtener los permisos desde el rol del usuario
        Set<GrantedAuthority> authorities = user.getRole().getPermissions().stream()
                .map(permission -> permission.getName())
                // obtener el nombre del permiso
                .map(SimpleGrantedAuthority::new) // convertirlo en un objeto GrantedAuthority
                .collect(Collectors.toSet());

        // Retornar un UserDetails que Spring Security pueda usar
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
