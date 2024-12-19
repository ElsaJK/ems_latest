package com.example.ems.service.Impl;

import com.example.ems.entity.Customer;
import com.example.ems.entity.Items;
import com.example.ems.mapper.ItemMapper;
import com.example.ems.model.CountDTO;
import com.example.ems.model.ItemDTO;
import com.example.ems.repository.CustomerRepository;
import com.example.ems.repository.ItemRepository;
import com.example.ems.service.ItemService;
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
public class ItemServiceImpl implements ItemService {

@Autowired
    ItemRepository itemRepository;
@Autowired
    CustomerRepository customerRepository;

@Autowired
    ItemMapper itemMapper;

    public ItemDTO create(ItemDTO itemDTO) {
            Items items = itemMapper.dtoToEntity(itemDTO);
            if (items.getId() != null) {
                Optional<Items> optional = itemRepository.findById(items.getId());
                if (optional.isPresent()) {
                    Items existingMenu = optional.get();
                    items.setCreatedBy(existingMenu.getCreatedBy());
                    items.setCreatedDate(existingMenu.getCreatedDate());
                    items = itemRepository.increaseCount(itemDTO.getCount(),items.getName());
                }
            }
            itemDTO = itemMapper.entityToDto(itemRepository.save(items));
            return itemDTO;

        }

    @Override
    public CountDTO itemUpdate(ItemDTO itemDTO) {
        CountDTO countDTO = new CountDTO();
        Items items = itemRepository.findByName(itemDTO.getName());
        if("add".equals(itemDTO.getAction())){

          items = itemRepository.increaseCount(itemDTO.getCount(),items.getName());
          Optional<Customer> customerOptional = customerRepository.findByNameAndMobile(itemDTO.getCustomerName(),itemDTO.getCustomerMobile());
          Customer customer = new Customer();
          if(customerOptional.isPresent()){
             customer= customerOptional.get();
             customer.setReturnDate(itemDTO.getItemReturnedDate());
          }
        }else if("remove".equals(itemDTO.getAction())){
        items = itemRepository.decreaseCount(itemDTO.getCount(),items.getName());
            Optional<Customer> customerOptional = customerRepository.findByNameAndMobile(itemDTO.getCustomerName(),itemDTO.getCustomerMobile());
            Customer customer = new Customer();
            if(customerOptional.isPresent()){
                customer= customerOptional.get();
                customer.setRentedDate(itemDTO.getItemDisbursedDate());
            }
        }
        countDTO.setName(items.getName());
        countDTO.setBalance(items.getBalance());
        countDTO.setOutStock(items.getOutStock());
        countDTO.setTotalStock(items.getTotalStock());
        return countDTO;
    }

    @Override
    public Page<ItemDTO> findAllItems(Pageable pageable) {

            Page<Items> itemsList = itemRepository.findAll(pageable);
            List<ItemDTO> itemDTO = new ArrayList<>();
            itemsList.forEach(items -> itemDTO.add(new ItemDTO(items.getId(), items.getName(), items.getImage(), items.getTotalStock(), items.getOutStock(),items.getBalance())));
            return new PageImpl<>(itemDTO, pageable, itemDTO.size());
        }



}

