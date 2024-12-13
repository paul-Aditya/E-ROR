// ZoneRepository.java
package com.rorCitizenApplication.ROR.repository;

import com.rorCitizenApplication.ROR.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {
    boolean existsByZoneId(String zoneId);
}