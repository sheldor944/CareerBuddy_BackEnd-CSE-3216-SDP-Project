package com.example.demo.usermanagement.profileManagement.helperModels.heperRepository;

import com.example.demo.usermanagement.profileManagement.helperModels.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
