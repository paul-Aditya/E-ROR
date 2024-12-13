// ZoneWardMapping.java
package com.rorCitizenApplication.ROR.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "zone_and_ward_mapping")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZoneWardMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer wardNumber;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;
}