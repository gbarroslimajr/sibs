package com.sibs.business.interfaces;


import com.sibs.api.dto.UserDTO;
import com.sibs.domain.model.User;

import java.util.List;

/**
 * @author geraldobarrosjr
 */
public interface UserService extends BaseInterface<User, UserDTO> {

    List<User> findAllUsers();

    User findById(Long id);

    List<User> findByName(String username);

    List<User> findByEmail(String email);


}
