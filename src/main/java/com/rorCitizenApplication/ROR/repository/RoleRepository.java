// RoleRepository.java
package com.rorCitizenApplication.ROR.repository;

import com.rorCitizenApplication.ROR.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    boolean existsByRoleId(Integer roleId);
}