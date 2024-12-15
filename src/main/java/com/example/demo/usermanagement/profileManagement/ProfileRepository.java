package com.example.demo.usermanagement.profileManagement;

import com.example.demo.usermanagement.profileManagement.skill.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Profile findByUserId(UUID userId);

    @Query("SELECT p.skills FROM Profile p WHERE p.id = :profileId")
    Set<Skill> findSkillsByProfileId(@Param("profileId") UUID profileId);
}
