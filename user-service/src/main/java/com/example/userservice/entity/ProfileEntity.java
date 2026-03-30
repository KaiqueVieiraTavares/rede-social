package com.example.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String bio;
    @Column(name = "avatar_url")
    private String avatarUrl;
    private String location;
    private String website;
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
