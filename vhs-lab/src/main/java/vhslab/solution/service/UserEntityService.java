package vhslab.solution.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vhslab.solution.DAL.interfaces.UserEntityRepository;
import vhslab.solution.entities.model.UserEntity;

@Service
public class UserEntityService {
    @Autowired
    private UserEntityRepository userRepository;

    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
    public UserEntity updateUser(Long userId, UserEntity updatedUser) {
        UserEntity existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser = updatedUser;
            return userRepository.save(existingUser);
        }
        return null;
    }
}
