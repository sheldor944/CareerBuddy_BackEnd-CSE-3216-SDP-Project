package com.example.demo.usermanagement.profileManagement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Profile findByUserId(UUID user_id);
}
