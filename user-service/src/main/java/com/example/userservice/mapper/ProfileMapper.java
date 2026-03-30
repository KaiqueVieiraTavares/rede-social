package com.example.userservice.mapper;

import com.example.userservice.dto.request.profile.CreateProfileDTO;
import com.example.userservice.dto.request.profile.UpdateProfileDTO;
import com.example.userservice.dto.response.profile.ProfileResponseDTO;
import com.example.userservice.entity.ProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

     ProfileResponseDTO toDto(ProfileEntity profileEntity);
     @Mapping(target = "user", ignore = true)
     ProfileEntity toEntity(CreateProfileDTO createProfileDTO);
     @Mapping(target = "user", ignore = true)
     void updateProfile(UpdateProfileDTO updateProfileDTO, @MappingTarget ProfileEntity profileEntity);
}
