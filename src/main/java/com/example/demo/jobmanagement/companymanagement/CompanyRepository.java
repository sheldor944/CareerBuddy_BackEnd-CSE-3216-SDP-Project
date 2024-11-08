package com.example.demo.jobmanagement.companymanagement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<List<Company>> findByNameIgnoreCase(String name);
    @Query(value = "SELECT * FROM companies c WHERE " +
            "(:name IS NULL OR c.name ILIKE CONCAT('%', :name, '%')) " +
            "AND (:domain IS NULL OR c.domain ILIKE CONCAT('%', :domain, '%')) " +
            "AND (:location IS NULL OR c.location ILIKE CONCAT('%', :location, '%'))",
            nativeQuery = true)
    List<Company> findByMultipleFields(@Param("name") String name,
                                       @Param("domain") String domain,
                                       @Param("location") String location);


}
