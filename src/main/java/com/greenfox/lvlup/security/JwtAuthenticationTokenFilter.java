package com.greenfox.lvlup.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenfox.lvlup.exception.CustomAuthenticationException;
import com.greenfox.lvlup.exception.DefaultExceptionHandler;
import com.greenfox.lvlup.exception.ErrorMessage;
import com.greenfox.lvlup.exception.GeneralException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

  @Autowired
  private DefaultExceptionHandler exceptionHandler;

  public JwtAuthenticationTokenFilter () {
    super("/api/**");
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
    String header = request.getHeader("Authentication");

    if (header == null || !header.startsWith("Bearer ")) {
      throw new RuntimeException("JWT token is missing");
    }

    String authenticationToken = header.substring(7);

    JwtAccessToken token = new JwtAccessToken(authenticationToken);
    return getAuthenticationManager().authenticate(token);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request,
                                          HttpServletResponse response,
                                          FilterChain chain,
                                          Authentication authResult) throws IOException, ServletException {
    super.successfulAuthentication(request, response, chain, authResult);
    chain.doFilter(request, response);
  }

// this is new
  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
    HttpServletResponse response = (HttpServletResponse) res;
    try {
     super.doFilter(req, res, chain);
   }
   catch (RuntimeException e) {
     ErrorMessage message = new ErrorMessage(e.getLocalizedMessage());
     response.setStatus(401);

     ObjectMapper mapper = new ObjectMapper();
     PrintWriter out = res.getWriter();
     out.print(mapper.writeValueAsString(message));
     out.flush();
   }
  }
}
