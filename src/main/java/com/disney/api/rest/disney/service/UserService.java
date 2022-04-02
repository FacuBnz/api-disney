package com.disney.api.rest.disney.service;

import com.disney.api.rest.disney.entity.User;
import com.disney.api.rest.disney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private EmailService emailService;

    @Transactional
    public String create(User u) throws Exception{
        if(userRepository.existsByEmail(u.getEmail())){
            throw new Exception("There is already a user associated with the entered email address");
        }
        User user = new User();
        user.setEmail(u.getEmail());
        user.setPassword(encoder.encode(u.getPassword()));
        userRepository.save(user);
        emailService.sendTextEmail(u.getEmail());
        return "User created successfully";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("Email not registered: "+email));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }
}
