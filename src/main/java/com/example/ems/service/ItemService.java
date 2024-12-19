package com.example.ems.service;

import com.example.ems.model.CountDTO;
import com.example.ems.model.ItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {
    ItemDTO create(ItemDTO itemDTO);
    CountDTO itemUpdate(ItemDTO itemDTO);

    Page<ItemDTO> findAllItems(Pageable pageable);
}
