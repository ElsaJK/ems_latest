package com.example.ems.mapper;

import com.example.ems.entity.Items;
import com.example.ems.model.ItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ItemMapper {
    Items dtoToEntity(ItemDTO itemDTO);
    ItemDTO entityToDto(Items items);
    //List<ItemDTO> mapList(List<Items> items);
}
