package com.itstime.allpasstival.configuration;

import com.itstime.allpasstival.domain.entity.User;
import com.itstime.allpasstival.exception.ErrorCode;
import com.itstime.allpasstival.service.UserService;
import com.itstime.allpasstival.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    private final String secretKey;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorizationHeader : {}", authorizationHeader);

        if(authorizationHeader==null || !authorizationHeader.startsWith("Bearer")){
            request.setAttribute("exception", ErrorCode.INVALID_PERMISSION);
            filterChain.doFilter(request,response);
            return;
        }

        String token;
        try {
            token = authorizationHeader.split(" ")[1];
        }
        catch (Exception e){
            request.setAttribute("exception", ErrorCode.INVALID_PERMISSION);
            filterChain.doFilter(request,response);
            return;
        }
        Integer userId;
        try {
            userId = JwtTokenUtil.getUserId(token,secretKey);
            log.info("user Name :{}", userId);
        }
        catch (Exception e){
            request.setAttribute("exception", ErrorCode.INVALID_TOKEN);
            filterChain.doFilter(request,response);
            return;
        }

        User user = userService.getUserByUserId(userId);
        log.info("is this user is admin: {}",user.isAdmin());

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserId(), null);
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        request.setAttribute("exception", null);
        filterChain.doFilter(request, response);
        log.info("user Name :{}", userId);

    }
}