package com.example.ems.service;

import com.example.ems.exceptions.APException;
import com.example.ems.model.CustomerDTO;
import com.example.ems.model.ItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerDTO create(CustomerDTO userDTO) throws APException;
    Page<CustomerDTO> findAllCustomers(Pageable pageable);
}
