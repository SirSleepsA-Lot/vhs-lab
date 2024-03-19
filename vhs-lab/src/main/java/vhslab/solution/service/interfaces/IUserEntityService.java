package vhslab.solution.service.interfaces;

import vhslab.solution.entities.dto.UserEntityDto;
import vhslab.solution.entities.dto.VhsEntityDto;

public interface IUserEntityService {
    UserEntityDto createUser(UserEntityDto vhsDto);
    UserEntityDto getUserById(Long id);
    UserEntityDto updateUser(Long id, UserEntityDto updatedVhsDto);
    void deleteUserById(Long id);
}
