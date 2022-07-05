package com.sibs.business.service;

import com.sibs.api.dto.UserDTO;
import com.sibs.business.interfaces.UserService;
import com.sibs.domain.exception.BusinessException;
import com.sibs.domain.model.User;
import com.sibs.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author geraldobarrosjr
 */

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) { return userRepository.findById(id).orElseThrow(() -> new BusinessException("User not found with ID " + id, "User Not Found")); }

    @Override
    public List<User> findByName(String name) { return userRepository.findByNameContaining(name); }

    @Override
    public List<User> findByEmail(String email) {
        return userRepository.findByEmailContaining(email);
    }



    @Override
    public User create(UserDTO user) {

        User userCreated = userRepository.findByEmail(user.getEmail());
        if (userCreated != null) {
            throw new BusinessException("User already exists with email " + user.getEmail(), "User Already Exists");
        }

        return userRepository.save(toEntity(user));

    }


    @Override
    public User update(Long id, UserDTO request) {

        User user = userExists(id);
        if (user != null) {
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            return userRepository.save(user);
        }

        return null;
    }


    @Override
    public void delete(Long id) {
        User user = userExists(id);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Override
    public UserDTO toModel(User request) {
        return modelMapper.map(request, UserDTO.class);
    }

    @Override
    public User toEntity(UserDTO request) {
        return modelMapper.map(request, User.class);
    }

    @Override
    public List<UserDTO> toModelList(List<User> request) {
        return request.stream().map(this::toModel).collect(java.util.stream.Collectors.toList());
    }

    public User userExists(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }


}
