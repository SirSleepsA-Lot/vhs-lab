package vhslab.solution.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vhslab.solution.DAL.interfaces.UserEntityRepository;
import vhslab.solution.entities.dto.UserEntityDto;
import vhslab.solution.entities.model.UserEntity;
import vhslab.solution.service.interfaces.IUserEntityService;

@Service
public class UserEntityService implements IUserEntityService {
    private final ModelMapper modelMapper;
    private final UserEntityRepository userRepository;
    @Autowired
    public UserEntityService(UserEntityRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public UserEntityDto createUser(UserEntityDto user) {
        var newUser = userRepository.save(modelMapper.map(user, UserEntity.class));
        return modelMapper.map(newUser, UserEntityDto.class);
    }
    @Override
    public UserEntityDto getUserById(Long userId) {
        var user = userRepository.findById(userId).orElse(null);
        return modelMapper.map(user, UserEntityDto.class);
    }
    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
    @Override
    public UserEntityDto updateUser(Long userId, UserEntityDto userToUpdate) {
        UserEntity existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser = modelMapper.map(userToUpdate, UserEntity.class);
            var updatedUser = userRepository.save(existingUser);
            return modelMapper.map(updatedUser, UserEntityDto.class);
        }
        return null;
    }
}
