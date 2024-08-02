package com.example.clinic.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int sex;
    @Column(unique = true)
    private String document;
    private String phone;
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String email;
    private String address;
    private int number;
    private String district;
    private String complement;
    private String zip;
    private String city;
    private String state;
    private String information;
    private LocalDateTime createdAt;

    public Patient(){}
    public Patient(Long id,
                   String name,
                   int sex,
                   String document,
                   String phone,
                   LocalDate dateOfBirth,
                   String email,
                   String address,
                   int number,
                   String district,
                   String complement,
                   String zip,
                   String city,
                   String state,
                   String information,
                   LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.document = document;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.address = address;
        this.number = number;
        this.district = district;
        this.complement = complement;
        this.zip = zip;
        this.city = city;
        this.state = state;
        this.information = information;
        this.createdAt = createdAt;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
