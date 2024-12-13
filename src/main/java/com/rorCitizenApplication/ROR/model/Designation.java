package com.rorCitizenApplication.ROR.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "designation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String designationId;

    @Column(nullable = false)
    private String designationName;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}