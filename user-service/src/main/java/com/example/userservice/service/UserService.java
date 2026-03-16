package com.example.userservice.service;

import com.example.userservice.dto.request.UpdateUserDTO;
import com.example.userservice.dto.response.UserResponseDTO;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserResponseDTO getUser(UUID userId){
        return userMapper.toDto(findUserOrThrow(userId));
    }
    public List<UserResponseDTO> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }
    public UserResponseDTO updateUser(UUID userId, UpdateUserDTO updateUserDTO){
        var user = findUserOrThrow(userId);
        userMapper.updateUser(updateUserDTO, user);
        return userMapper.toDto(user);
    }

    private UserEntity findUserOrThrow(UUID userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found"));
    }
}
