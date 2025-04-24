package com.nathan.nl_finances.config.security;

import com.nathan.nl_finances.repositories.UserRepository;
import com.nathan.nl_finances.services.AuthService;
import com.nathan.nl_finances.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;
    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;

    //    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        var token = this.recoverToken(request);
//        if(token != null){
//            var email = tokenService.validateToken(token);
//            UserDetails user = authService.loadUserByUsername(email);
//
//            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//        }
//        filterChain.doFilter(request, response);
//    }
@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {

    var token = recoverToken(request);
    if(token != null) {
        var email = tokenService.validateToken(token);
        var accountId = tokenService.getAccountIdFromToken(token);

        // Busca o usuário e cria a autenticação
        var user = userRepository.findByEmailAddress(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var authentication = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );

        // Configura o contexto do Spring Security
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Configura nosso contexto customizado
        UserContext.setContext(email, accountId);
    }

    filterChain.doFilter(request, response);

    // Limpa o contexto após a requisição
    if (token != null) {
        SecurityContextHolder.clearContext();
        UserContext.clear();
    }
}


    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }
}
