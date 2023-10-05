package com.mertfidan.schoolmanagement.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService {
  //UserDetailsService interface has a method to load User by username and returns a UserDetails object
  UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}