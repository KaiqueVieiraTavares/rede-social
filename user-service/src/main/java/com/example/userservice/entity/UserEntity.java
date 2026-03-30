package com.example.userservice.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "user_name", unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private ProfileEntity profileEntity;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void setProfileEntity(ProfileEntity profile){
        if(this.profileEntity!=null){
            this.profileEntity.setUser(null);
        }
        if(profile!=null){
            profile.setUser(this);
        }
        this.profileEntity = profile;
    }
}
