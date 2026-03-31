package com.example.userservice.mapper;

import com.example.userservice.dto.request.user.CreateUserDTO;
import com.example.userservice.dto.request.user.UpdateUserDTO;
import com.example.userservice.dto.response.user.UserResponseDTO;
import com.example.userservice.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDto(UserEntity userEntity);
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profileEntity", ignore = true)
    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(CreateUserDTO createUserDTO);
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "profileEntity", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateUser(UpdateUserDTO updateUserDTO, @MappingTarget UserEntity userEntity);
}
