package com.example.citylist.service;

import com.example.citylist.model.User;
import com.example.citylist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to access database and retrieve users properties
 */

@Service
public class GroupUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public GroupUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(name);
        return user.map(GroupUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(name + " Not Found"));
    }
}
