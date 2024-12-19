package com.example.ems.mapper;

import com.example.ems.entity.Customer;
import com.example.ems.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {
    CustomerDTO entityToDto(Customer customer);
}
