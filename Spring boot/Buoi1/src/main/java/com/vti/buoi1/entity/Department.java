package com.vti.buoi1.entity;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @Column(name ="department_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name= "deparment_name", nullable = false, length =50)
    private String name;
}
