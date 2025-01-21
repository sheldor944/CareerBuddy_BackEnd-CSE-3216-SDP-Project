package com.example.demo.usermanagement.profileManagement.research;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResearchRepository extends JpaRepository<Research, UUID> {

}
