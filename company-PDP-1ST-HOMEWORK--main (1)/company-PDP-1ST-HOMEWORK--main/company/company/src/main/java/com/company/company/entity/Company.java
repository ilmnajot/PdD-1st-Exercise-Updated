package com.company.company.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String corpName;
    @Column(nullable = false, unique = true)
    private String directorName;
    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "company")
    private List<Department> departments;



}
