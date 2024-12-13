// ZoneWardMappingRepository.java
package com.rorCitizenApplication.ROR.repository;

import com.rorCitizenApplication.ROR.model.Zone;
import com.rorCitizenApplication.ROR.model.ZoneWardMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneWardMappingRepository extends JpaRepository<ZoneWardMapping, Long> {
    boolean existsByWardNumberAndZone(Integer wardNumber, Zone zone);
}