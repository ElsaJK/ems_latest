package com.example.ems.service;

import com.amazonaws.SdkClientException;
import com.example.ems.exceptions.APException;
import com.example.ems.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.IOException;
import java.util.List;

public interface UserService {
    UserRequestDTO create(UserRequestDTO userDTO) throws APException;

    List<UserRequestDTO> findAll();

    UserRequestDTO findById(String id);

    UserRequestDTO findByUserName(String userName);

    Boolean deleteById(String id);

    UserFilterResponse fetchUser(UserFilterRequestDTO userFilterRequest, Pageable pageable);

}
