package com.example.ems.controller;

import com.example.ems.exceptions.APException;
import com.example.ems.model.ApiResponse;
import com.example.ems.model.CustomerDTO;
import com.example.ems.model.ItemDTO;
import com.example.ems.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<CustomerDTO>> create(@RequestBody CustomerDTO customerDTO) throws APException {
        ApiResponse apiResponse = new ApiResponse<>();
        try {
            apiResponse.setResponse(customerService.create(customerDTO));
            apiResponse.setMessage("Customer Created Successfully");
            apiResponse.setSuccess(Boolean.TRUE);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } catch (APException e) {
            apiResponse.setErrorCode(e.getCode());
            apiResponse.setSuccess(Boolean.FALSE);
            apiResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            apiResponse.setMessage("Error occurred While Creating Customer:" + e.getMessage());
            apiResponse.setSuccess(Boolean.FALSE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
        }
    }


    @GetMapping(value = "/getCustomer")
    ResponseEntity<ApiResponse<Page<CustomerDTO>>> getAllCustomers(
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        Page<CustomerDTO> customerDTOS = customerService.
                findAllCustomers(PageRequest.of(page, size));
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setResponse(customerDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }
}
