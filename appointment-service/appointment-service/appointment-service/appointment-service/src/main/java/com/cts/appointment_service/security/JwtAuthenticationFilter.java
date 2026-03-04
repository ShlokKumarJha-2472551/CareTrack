package com.cts.appointment_service.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (tokenProvider.validateToken(token)) {
                Claims claims = tokenProvider.getClaims(token);
                Long userId = claims.get("userId", Long.class);
                String role = claims.get("role", String.class);
                if (!role.startsWith("ROLE_")) {
                    role = "ROLE_" + role;
                }
                List<GrantedAuthority> authorities =
                        List.of(new SimpleGrantedAuthority(role));
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(
                                userId, null, authorities);
                auth.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));
                SecurityContextHolder.getContext()
                        .setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
