package com.example.userservice.service;

import com.example.userservice.dto.request.user.UpdateUserDTO;
import com.example.userservice.dto.response.user.UserResponseDTO;
import com.example.userservice.entity.UserEntity;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private static final String CACHE_VALUE = "users";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Cacheable(value = CACHE_VALUE, key = "#userId")
    public UserResponseDTO getUser(UUID userId){
        return userMapper.toDto(findUserOrThrow(userId));
    }


    public Page<UserResponseDTO> getAllUsers(Pageable pageable){
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }
    @CachePut(value = CACHE_VALUE, key = "#userId")
    public UserResponseDTO updateUser(UUID userId, UpdateUserDTO updateUserDTO){
        var user = findUserOrThrow(userId);
        userMapper.updateUser(updateUserDTO, user);
        return userMapper.toDto(user);
    }
    private UserEntity findUserOrThrow(UUID userId){
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id: " + userId + " not found"));
    }
}
