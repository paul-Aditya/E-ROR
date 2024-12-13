// DesignationRepository.java
package com.rorCitizenApplication.ROR.repository;

import com.rorCitizenApplication.ROR.model.Designation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {
    boolean existsByDesignationId(String designationId);
}