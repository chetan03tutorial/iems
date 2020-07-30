package com.nataraj.management.employee.iems.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEE_DETAILS")
@Getter
@Setter
public class Employee {

    @Id
    @Column(name="EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_id_generator")
    @SequenceGenerator(name="employee_id_generator", sequenceName = "employee_seq")
    private Long id;
    @Column(name="EMPLOYEE_NAME")
    private String name;
    @Column(name="TITLE")
    private String title;
    @Column(name="BUSINESS_UNIT")
    private String bu;
    @Column(name="LOCATION")
    private String place;
    @Column(name="SUPERVISOR")
    private Long supervisor;
    @Column(name="COMPETENCIES")
    private String competencies;
    @Column(name="SALARY")
    private Double salary;



}
