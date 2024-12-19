package com.example.ems.controller;

import com.example.ems.exceptions.APException;
import com.example.ems.model.ApiResponse;
import com.example.ems.model.LoginRequestDTO;
import com.example.ems.model.UserDTO;
import com.example.ems.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDTO>> AuthenticateAndGetToken(@RequestBody LoginRequestDTO loginRequestDTO) throws APException {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            apiResponse.setResponse(loginService.login(loginRequestDTO));
            apiResponse.setMessage("LOGIN SUCCESSFULLY");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (APException e) {
            apiResponse.setResponse(false);
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setResponse(false);
            apiResponse.setMessage("Invalid credential");
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }
}
