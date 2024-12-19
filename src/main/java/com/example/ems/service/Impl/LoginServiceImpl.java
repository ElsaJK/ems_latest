package com.example.ems.service.Impl;

import com.example.ems.config.MessageConfiguration;
import com.example.ems.entity.User;
import com.example.ems.exceptions.APException;
import com.example.ems.mapper.UserEntityMapper;
import com.example.ems.model.JwtResponseDTO;
import com.example.ems.model.LoginRequestDTO;
import com.example.ems.model.UserDTO;
import com.example.ems.repository.UserRepository;
import com.example.ems.service.LoginService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class LoginServiceImpl implements LoginService {
    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userEntityRepository;

    @Autowired
    UserEntityMapper userEntityMapper;
    @Autowired
    MessageConfiguration messageConfiguration;

    @Override
    public UserDTO login(LoginRequestDTO loginRequestDTO) throws APException {
        try {
            Optional<User> userEntityOptional = userEntityRepository.findByUserName(loginRequestDTO.getUsername());
            if (userEntityOptional.isEmpty()) {
                throw new APException("AP-102", messageConfiguration.getError().get("AP-102"));
            }
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));

            JwtResponseDTO jwtResponseDTO = JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(loginRequestDTO.getUsername())).build();
            UserDTO userDTO = userEntityMapper.getDTOFromEntity(userEntityOptional.get());
            userDTO.setToken(jwtResponseDTO.getAccessToken());
            return userDTO;
        } catch (
                BadCredentialsException ex) {
            throw ex;
        }
    }
}