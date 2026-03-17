package com.RD_Technology.Internship;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "computer_device")
public class ComputerDevice {

    @Id
    @Column(name = "serial_no")
    private Integer serialNo;

    @Column(nullable = false)
    private String brand;

    private Double rating;

    private Long price;

    public ComputerDevice() {
    }

    public ComputerDevice(Integer serialNo, String brand, Double rating, Long price) {
        this.serialNo = serialNo;
        this.brand = brand;
        this.rating = rating;
        this.price = price;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}

