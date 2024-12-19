package com.example.ems.service.Impl;

import com.example.ems.config.MessageConfiguration;
import com.example.ems.entity.Status;
import com.example.ems.entity.User;
import com.example.ems.exceptions.APException;
import com.example.ems.exceptions.DataNotFoundException;
import com.example.ems.exceptions.DuplicateDataException;
import com.example.ems.mapper.UserEntityMapper;
import com.example.ems.model.UserFilterRequestDTO;
import com.example.ems.model.UserFilterResponse;
import com.example.ems.model.UserRequestDTO;
import com.example.ems.repository.UserRepository;
import com.example.ems.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private final UserEntityMapper userMapper;
    MessageConfiguration messageConfiguration;
    private PasswordEncoder passwordEncoder;

    UserServiceImpl(UserRepository userRepository, UserEntityMapper userMapper, MessageConfiguration messageConfiguration, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.messageConfiguration = messageConfiguration;
        this.passwordEncoder = passwordEncoder;
    }

    public UserRequestDTO create(UserRequestDTO userRequestDTO) throws APException {
        User user = userMapper.dtoToEntity(userRequestDTO);
        if (isDuplicateUserName(userRequestDTO)) {
            throw new DuplicateDataException("AP-100", messageConfiguration.getError().get("AP-100"));
        } else {
            if (userRequestDTO.getId() != null) {
                Optional<User> userOptional = userRepository.findById(userRequestDTO.getId());
                if (userOptional.isPresent()) {
                    User existingUser = userOptional.get();
                    user.setCreateBy(existingUser.getCreateBy());
                    user.setCreatedDate(existingUser.getCreatedDate());
                    user.setPassword(existingUser.getPassword());
                } else {
                    throw new DataNotFoundException("AP-101", messageConfiguration.getError().get("AP-101"));
                }
            } else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
        return userMapper.entityToDto(userRepository.save(user));

    }

    private boolean isDuplicateUserName(UserRequestDTO userDTO) {
        if (userDTO.getId() != null) {
            return this.userRepository
                    .findByIdNotAndUserName(userDTO.getId(), userDTO.getUserName().trim()).isPresent();
        } else {
            return this.userRepository
                    .findByUserName(userDTO.getUserName().trim()).isPresent();
        }
    }

    @Override
    public List<UserRequestDTO> findAll() {
        List<User> user = userRepository.findAll();
        return userMapper.mapList(user);

    }

    @Override
    public UserRequestDTO findById(String id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return userMapper.entityToDto(optional.get());
        }
        throw new DataNotFoundException("AP-101", messageConfiguration.getError().get("AP-101"));
    }

    public Boolean deleteById(String id) {
        Optional<User> OptionalUser = userRepository.findById(id);
        if (OptionalUser.isPresent()) {
            User user = OptionalUser.get();
            user.setStatus(Status.Inactive);
            userRepository.save(user);
        } else {
            throw new DataNotFoundException("AP-101", messageConfiguration.getError().get("AP-101"));
        }
        return true;
    }

    @Override
    public UserFilterResponse fetchUser(UserFilterRequestDTO userFilterRequest, Pageable pageable) {
        Page<User> userEntities = userRepository.fetchUsers(
                userFilterRequest.getUserName(),
                userFilterRequest.getName(),
                userFilterRequest.getEmail(),
                userFilterRequest.getRoleName(),
                userFilterRequest.getRoleCode(),
                pageable);
        UserFilterResponse userFilterResponse = new UserFilterResponse();
        userFilterResponse.setCount(userEntities.getTotalElements());
        userFilterResponse.setPageCount(userEntities.getTotalPages());
        userFilterResponse.setUserList(userMapper.mapList(userEntities.getContent()));
        return userFilterResponse;
    }


    @Override
    public UserRequestDTO findByUserName(String userName) {
        Optional<User> userEntityOptional = userRepository.findByUserName(userName);
        if (userEntityOptional.isPresent()) {
            return userMapper.entityToDto(userEntityOptional.get());
        }

        throw new DataNotFoundException("AP-101", messageConfiguration.getError().get("AP-101"));
    }

}

