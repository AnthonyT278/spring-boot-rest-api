package com.example.demo.security;

import jakarta.servlet.*; import jakarta.servlet.http.*; import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; import org.springframework.security.core.context.SecurityContextHolder; import org.springframework.security.core.userdetails.UserDetails; import org.springframework.stereotype.Component; import org.springframework.web.filter.OncePerRequestFilter; import java.io.IOException;
@Component public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private final JwtService jwt; private final UserDetailsServiceImpl users; public JwtAuthenticationFilter(JwtService jwt, UserDetailsServiceImpl users){this.jwt=jwt;this.users=users;}
  protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws ServletException,IOException { String h=req.getHeader("Authorization"); if(h!=null&&h.startsWith("Bearer ")) try { String token=h.substring(7), email=jwt.username(token); UserDetails u=users.loadUserByUsername(email); if(jwt.valid(token,u)) SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(u,null,u.getAuthorities())); } catch(Exception ignored){} chain.doFilter(req,res); }
}
