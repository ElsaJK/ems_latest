package com.example.ems.controller;

import com.example.ems.exceptions.APException;
import com.example.ems.model.*;
import com.example.ems.service.UserService;
import com.example.ems.util.PagingUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@SecurityRequirement(name = "jwt")
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    PagingUtils pagingUtils;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<UserRequestDTO>> create(@RequestBody UserRequestDTO userDTO) throws APException{
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            apiResponse.setResponse(userService.create(userDTO));
            apiResponse.setMessage("User Created Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (APException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Error occurred While Creating User:" + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<ApiResponse<List<UserRequestDTO>>> findAll() {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            apiResponse.setResponse(userService.findAll());
            apiResponse.setMessage("User Details Fetched Successfully ");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (APException e) {
            e.printStackTrace();
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Error occurred While Fetching User Details" + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<ApiResponse<UserRequestDTO>> findById(@RequestParam(value = "id") String id) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setResponse(userService.findById(id));
            apiResponse.setMessage("User fetched successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (APException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Failed To Fetch User : " + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }


    @GetMapping("/find-by-username")
    public ResponseEntity<ApiResponse<UserRequestDTO>> findByUserName(@RequestParam("userName") String userName) {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            apiResponse.setResponse(userService.findByUserName(userName));
            apiResponse.setMessage("User Fetched Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (APException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Failed To Fetch User:" + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<Boolean>> deleteById(@RequestParam(value = "id") String id) throws APException {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setResponse(userService.deleteById(id));
            apiResponse.setMessage(" User Deleted Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (APException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Failed to Delete User:" + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }

    @PostMapping("/fetch-user")
    public ResponseEntity<ApiResponse<UserFilterResponse>> fetchUser(@RequestBody UserFilterRequestDTO userFilterRequest, @RequestParam(required = false) Integer page,
                                                                     @RequestParam(required = false) Integer pageLength) {
        ApiResponse apiResponse = new ApiResponse<>();
        final Pageable pageable = PageRequest.of(pagingUtils.getNextPage(page), pagingUtils.getPageLength(pageLength));
        try {
            apiResponse.setResponse(userService.fetchUser(userFilterRequest, pageable));
            apiResponse.setMessage("User Fetched Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (APException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Failed To Fetch User:" + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }
}
