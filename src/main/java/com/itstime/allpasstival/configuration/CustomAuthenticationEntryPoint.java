package com.itstime.allpasstival.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itstime.allpasstival.domain.dto.ErrorResponse;
import com.itstime.allpasstival.domain.dto.Response;
import com.itstime.allpasstival.exception.ErrorCode;
import org.json.JSONException;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Configuration
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ErrorCode exception = (ErrorCode) request.getAttribute("exception");
        if(exception!=null){
            try {
                setResponse(response,exception);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException, JSONException {
        ObjectMapper mapper = new ObjectMapper();
        ErrorResponse errorResponse = new ErrorResponse(errorCode,errorCode.getMessage());
        Response<ErrorResponse> errorResponseResponse = Response.error(errorResponse);
        String jsonInString = mapper.writeValueAsString(errorResponseResponse);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorCode.getStatus().value());
        response.getWriter().print(jsonInString);
    }
}
