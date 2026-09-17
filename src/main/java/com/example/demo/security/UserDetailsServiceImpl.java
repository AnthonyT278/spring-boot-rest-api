package com.example.demo.security;

import com.example.demo.repository.UserRepository; import org.springframework.security.core.userdetails.*; import org.springframework.stereotype.Service;
@Service public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserRepository users; public UserDetailsServiceImpl(UserRepository users){this.users=users;}
  public UserDetails loadUserByUsername(String email) { var u=users.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found")); return User.withUsername(u.getEmail()).password(u.getPassword()).roles(u.getRole()).build(); }
}
