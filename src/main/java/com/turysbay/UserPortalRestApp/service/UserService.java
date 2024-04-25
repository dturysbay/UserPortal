package com.turysbay.UserPortalRestApp.service;

import com.turysbay.UserPortalRestApp.entity.User;
import com.turysbay.UserPortalRestApp.exceptions.UserAlreadyExistsException;
import com.turysbay.UserPortalRestApp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User userRegistration(User user ) throws UserAlreadyExistsException {
        if (userRepository.findByLogin(user.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException("User with this login already exists.");
        }
//        TODO: MAKE HASHCODE FOR PASSWWORD, LATER INCLUDE SPRING SECURITY
        return userRepository.save(user);
    }

    public Boolean authenticateUser(String login,String password){
        Optional<User> optionalUser = userRepository.findByLogin(login);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
//            TODO: MAKE TOKEN GENERATION
            return user.getPassword().equals(password);
        }
        return false;
    }

    public Optional<User> findByUserId(Long id){
        return userRepository.findById(id);
    }

}
