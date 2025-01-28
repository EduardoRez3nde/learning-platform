package com.rezende.learn.services;

import com.rezende.learn.entities.Role;
import com.rezende.learn.entities.User;
import com.rezende.learn.projections.UserDetailsProjection;
import com.rezende.learn.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorizationUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = userRepository.findByUsername(username);
        if (result.isEmpty())
            throw new UsernameNotFoundException("User Not Found");

        User user = new User();
        user.setEmail(result.getFirst().getUsername());
        user.setPassword(result.getFirst().getPassword());

        result.forEach(authority -> {
            Role role = new Role();
            role.setId(authority.getRoleId());
            role.setAuthority(authority.getAuthority());
            user.getRoles().add(role);
        });
        return user;
    }
}
