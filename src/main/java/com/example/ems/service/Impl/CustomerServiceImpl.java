package com.example.ems.service.Impl;

import com.example.ems.entity.Customer;
import com.example.ems.entity.Items;
import com.example.ems.exceptions.APException;
import com.example.ems.exceptions.DataNotFoundException;
import com.example.ems.mapper.CustomerMapper;
import com.example.ems.model.CustomerDTO;
import com.example.ems.model.ItemDTO;
import com.example.ems.repository.CustomerRepository;
import com.example.ems.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) throws APException {

        Customer customer = new Customer();
        Optional<Customer> customerOptional = customerRepository.findByNameAndMobile(customerDTO.getName(),customerDTO.getMobile());
        if(customerOptional.isPresent()){
            customer = customerOptional.get();
        }
        customer.setName(customerDTO.getName());
        customer.setMobile(customerDTO.getMobile());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());

        return customerMapper.entityToDto(customerRepository.save(customer));
    }

    @Override
    public Page<CustomerDTO> findAllCustomers(Pageable pageable) {
        Page<Customer> customersList = customerRepository.findByreturnDateNull(pageable);
        List<CustomerDTO> customerDTO = new ArrayList<>();
        customersList.forEach(customer -> customerDTO.add(new CustomerDTO(customer.getId(), customer.getName(),customer.getAddress(),customer.getMobile(),customer.getEmail(),customer.getRentedDate(),customer.getReturnDate())));
        return new PageImpl<>(customerDTO, pageable, customerDTO.size());
    }
}
