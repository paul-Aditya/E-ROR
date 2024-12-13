package com.rorCitizenApplication.ROR.config;

import com.rorCitizenApplication.ROR.model.Designation;
import com.rorCitizenApplication.ROR.model.Role;
import com.rorCitizenApplication.ROR.model.Zone;
import com.rorCitizenApplication.ROR.model.ZoneWardMapping;
import com.rorCitizenApplication.ROR.repository.DesignationRepository;
import com.rorCitizenApplication.ROR.repository.RoleRepository;
import com.rorCitizenApplication.ROR.repository.ZoneRepository;
import com.rorCitizenApplication.ROR.repository.ZoneWardMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Configuration
public class DataInitializer {
    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private ZoneWardMappingRepository zoneWardMappingRepository;

    @Autowired
    private DesignationRepository designationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        try {
            List<Zone> zones = Arrays.asList(
                    new Zone(null, "CZ", "Central Zone"),
                    new Zone(null, "NZ", "North Zone"),
                    new Zone(null, "EZ", "East Zone"),
                    new Zone(null, "SZ", "South Zone"),
                    new Zone(null, "HO", "Head Office")
            );
            for (Zone zone : zones) {
                if (!zoneRepository.existsByZoneId(zone.getZoneId())) {
                    zoneRepository.save(zone);
                }
            }

            // Fetch the saved zones from the repository
            List<Zone> savedZones = zoneRepository.findAll();

            List<ZoneWardMapping> zoneWardMappings = Arrays.asList(
                    new ZoneWardMapping(null, 1, savedZones.get(1)),
                    new ZoneWardMapping(null, 2, savedZones.get(1)),
                    new ZoneWardMapping(null, 3, savedZones.get(1)),
                    new ZoneWardMapping(null, 4, savedZones.get(1)),
                    new ZoneWardMapping(null, 5, savedZones.get(1)),
                    new ZoneWardMapping(null, 6, savedZones.get(1)),
                    new ZoneWardMapping(null, 7, savedZones.get(1)),
                    new ZoneWardMapping(null, 8, savedZones.get(1)),
                    new ZoneWardMapping(null, 9, savedZones.get(2)),
                    new ZoneWardMapping(null, 10, savedZones.get(2)),
                    new ZoneWardMapping(null, 11, savedZones.get(1)),
                    new ZoneWardMapping(null, 12, savedZones.get(1)),
                    new ZoneWardMapping(null, 13, savedZones.get(1)),
                    new ZoneWardMapping(null, 14, savedZones.get(0)),
                    new ZoneWardMapping(null, 15, savedZones.get(0)),
                    new ZoneWardMapping(null, 16, savedZones.get(0)),
                    new ZoneWardMapping(null, 17, savedZones.get(0)),
                    new ZoneWardMapping(null, 18, savedZones.get(0)),
                    new ZoneWardMapping(null, 19, savedZones.get(0)),
                    new ZoneWardMapping(null, 20, savedZones.get(0)),
                    new ZoneWardMapping(null, 21, savedZones.get(2)),
                    new ZoneWardMapping(null, 22, savedZones.get(0)),
                    new ZoneWardMapping(null, 23, savedZones.get(2)),
                    new ZoneWardMapping(null, 24, savedZones.get(2)),
                    new ZoneWardMapping(null, 25, savedZones.get(2)),
                    new ZoneWardMapping(null, 26, savedZones.get(2)),
                    new ZoneWardMapping(null, 27, savedZones.get(2)),
                    new ZoneWardMapping(null, 28, savedZones.get(2)),
                    new ZoneWardMapping(null, 29, savedZones.get(2)),
                    new ZoneWardMapping(null, 30, savedZones.get(2)),
                    new ZoneWardMapping(null, 31, savedZones.get(0)),
                    new ZoneWardMapping(null, 32, savedZones.get(0)),
                    new ZoneWardMapping(null, 33, savedZones.get(3)),
                    new ZoneWardMapping(null, 34, savedZones.get(0)),
                    new ZoneWardMapping(null, 35, savedZones.get(0)),
                    new ZoneWardMapping(null, 36, savedZones.get(0)),
                    new ZoneWardMapping(null, 37, savedZones.get(3)),
                    new ZoneWardMapping(null, 38, savedZones.get(3)),
                    new ZoneWardMapping(null, 39, savedZones.get(3)),
                    new ZoneWardMapping(null, 40, savedZones.get(3)),
                    new ZoneWardMapping(null, 41, savedZones.get(3)),
                    new ZoneWardMapping(null, 42, savedZones.get(3)),
                    new ZoneWardMapping(null, 43, savedZones.get(3)),
                    new ZoneWardMapping(null, 44, savedZones.get(3)),
                    new ZoneWardMapping(null, 45, savedZones.get(3)),
                    new ZoneWardMapping(null, 46, savedZones.get(3)),
                    new ZoneWardMapping(null, 47, savedZones.get(3)),
                    new ZoneWardMapping(null, 48, savedZones.get(3)),
                    new ZoneWardMapping(null, 49, savedZones.get(3)),
                    new ZoneWardMapping(null, 50, savedZones.get(3)),
                    new ZoneWardMapping(null, 51, savedZones.get(3)),
                    new ZoneWardMapping(null, 1111, savedZones.get(4))
            );
            for (ZoneWardMapping mapping : zoneWardMappings) {
                if (!zoneWardMappingRepository.existsByWardNumberAndZone(mapping.getWardNumber(), mapping.getZone())) {
                    zoneWardMappingRepository.save(mapping);
                }
            }

            List<Role> roles = List.of(
                    new Role(null, 1, "Applicant"),
                    new Role(null, 2, "Verifier"),
                    new Role(null, 3, "Approver"),
                    new Role(null, 4, "Administrator")
            );

            for (Role role : roles) {
                if (!roleRepository.existsByRoleId(role.getRoleId())) {
                    roleRepository.save(role);
                }
            }

            List<Role> savedRoles = roleRepository.findAll();

            List<Designation> designations = List.of(
                    new Designation(null, "sys_adm", "System Admin", savedRoles.get(3)),
                    new Designation(null, "sys_ana", "System Analyst", savedRoles.get(3)),
                    new Designation(null, "ast_mun_com", "Assistant Municipal Commissioner", savedRoles.get(2)),
                    new Designation(null, "wrd_sec", "Ward Secretary", savedRoles.get(1)),
                    new Designation(null, "ctz", "Citizen", savedRoles.get(0))
            );


            for (Designation designation : designations) {
                if (!designationRepository.existsByDesignationId(designation.getDesignationId())) {
                    designationRepository.save(designation);
                }
            }


        } catch (Exception e) {
            System.out.println("Error initializing data: "+ e);
        }
    }
}