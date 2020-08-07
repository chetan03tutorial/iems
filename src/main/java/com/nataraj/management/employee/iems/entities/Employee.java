package com.nataraj.management.employee.iems.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="EMPLOYEE_DETAILS")

public class Employee implements Serializable {

    private static final long SerialVersionUID=10l;

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

    public Employee(){

    }
    
    public Employee(Long id, String name, String place, String title, String bu, Double salary) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.bu = bu;
        this.place = place;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBu() {
        return bu;
    }

    public void setBu(String bu) {
        this.bu = bu;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Long supervisor) {
        this.supervisor = supervisor;
    }

    public String getCompetencies() {
        return competencies;
    }

    public void setCompetencies(String competencies) {
        this.competencies = competencies;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Employee updateSalary(Double percentage){
        this.salary = salary + (percentage*salary/100) ;
        return this;
    }

    public String toString(){
        return "Employee details are = " + this.id + ", employee name " + this.name + " = ";
    }
}
