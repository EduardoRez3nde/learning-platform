package com.rezende.learn.config;

import com.rezende.learn.entities.Role;
import com.rezende.learn.entities.User;
import com.rezende.learn.projections.UserDetailsProjection;
import com.rezende.learn.repositories.UserRepository;
import com.rezende.learn.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if (token != null) {
            String login = tokenService.validateToken(token);
            List<UserDetailsProjection> result = userRepository.findByUsername(login);

            if (!(result.isEmpty())) {
                User user = new User();
                user.setEmail(result.getFirst().getUsername());
                user.setPassword(result.getFirst().getPassword());

                result.forEach(authority -> {
                    Role role = new Role();
                    role.setAuthority(authority.getAuthority());
                    user.getRoles().add(role);
                });
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !(authorization.startsWith("Bearer "))) return null;
        return authorization.replace("Bearer ", "");
    }
}
