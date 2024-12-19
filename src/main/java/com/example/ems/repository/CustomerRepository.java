package com.example.ems.repository;

import com.example.ems.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
   Optional<Customer> findByNameAndMobile(String name, String mobile);

    @Query("SELECT c FROM Customer c WHERE c.returnDate is null")
   Page<Customer> findByreturnDateNull(Pageable pageable);


    @Query("SELECT c from Customer c where (lower(c.name) = lower(:name))")
    Optional<Customer> findByName(String name);

}
