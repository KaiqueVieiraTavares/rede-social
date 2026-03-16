package com.example.userservice.mapper;

import com.example.userservice.dto.request.CreateUserDTO;
import com.example.userservice.dto.request.UpdateUserDTO;
import com.example.userservice.dto.response.UserResponseDTO;
import com.example.userservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDto(UserEntity userEntity);
    UserEntity toEntity(CreateUserDTO createUserDTO);
    void updateUser(UpdateUserDTO updateUserDTO, @MappingTarget UserEntity userEntity);
}
