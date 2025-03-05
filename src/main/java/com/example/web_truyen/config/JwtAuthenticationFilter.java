package com.example.web_truyen.config;

import com.example.web_truyen.service.IUserService;
import com.example.web_truyen.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private IUserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtFromHeader(request);
        if(jwt != null && JwtUtils.validateJwt(jwt))
        {
            String username = JwtUtils.getUsername(jwt);
            UserDetails userDetails =userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    username,null,userDetails.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }

    private String getJwtFromHeader(HttpServletRequest request)
    {
        String authorization= request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer"))
        {
            return authorization.replace("Bearer","");
        }
        return null;
    }
}
