package com.eduardz.jobportal.services;

import com.eduardz.jobportal.entity.Users;
import com.eduardz.jobportal.repository.UsersRepository;
import com.eduardz.jobportal.utils.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    public CustomUserDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(username).orElseThrow(()
                -> new UsernameNotFoundException("User " + username + " not found"));
        return new CustomUserDetails(user);
    }
}
