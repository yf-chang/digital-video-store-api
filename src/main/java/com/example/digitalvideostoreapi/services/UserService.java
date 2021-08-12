package com.example.digitalvideostoreapi.services;

import com.example.digitalvideostoreapi.models.UserModel;
import com.example.digitalvideostoreapi.models.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserModel addUser(UserModel user) throws Exception
    {
        //account validation
        if(user.getEmail()==null){
            throw new Exception ("Email cannot be empty.");
        }

        if(user.getUsername()==null){
            throw new Exception ("Username cannot be empty.");
        }

        if(user.getPassword()==null){
            throw new Exception ("Password cannot be empty.");
        }

        if(user.getPassword().length()<6){
            throw new Exception("Password length has to be greater than 6.");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        UserModel insertedUser = userRepository.insert(user);
        return insertedUser;
    }

    public List<UserModel> getUsers()
    {
        return userRepository.findAll();
    }

    public Optional<UserModel> getAUser(String id) throws Exception
    {
        Optional<UserModel> user = userRepository.findById(id);
        if (!user.isPresent())
        {
            throw new Exception (" User with " + id + " is not found ");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserModel foundUser = userRepository.findByUsername(username);
        String userN = foundUser.getUsername();
        String password = foundUser.getPassword();
        return new User(userN, password, new ArrayList<>());
    }
}
