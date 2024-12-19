package com.example.ems.mapper;


import com.example.ems.entity.User;
import com.example.ems.model.UserDTO;
import com.example.ems.model.UserRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    User dtoToEntity(UserRequestDTO userDTO);

    UserRequestDTO entityToDto(User user);

    List<UserRequestDTO> mapList(List<User> userList);

    com.example.ems.model.UserDTO getDTOFromEntity(User userEntity);
}
