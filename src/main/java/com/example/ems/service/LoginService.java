package com.example.ems.service;

import com.example.ems.exceptions.APException;
import com.example.ems.model.LoginRequestDTO;
import com.example.ems.model.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    UserDTO login(LoginRequestDTO loginRequestDTO) throws APException;

}