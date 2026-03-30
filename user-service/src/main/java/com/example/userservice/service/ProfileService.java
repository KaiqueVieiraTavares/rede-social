package com.example.userservice.service;

import com.example.userservice.repository.ProfileRepository;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;


}
