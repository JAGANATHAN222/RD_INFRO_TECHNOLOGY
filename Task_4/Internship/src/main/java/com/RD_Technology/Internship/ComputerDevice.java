package com.RD_Technology.Internship;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ComputerDevice {
    @Id
    int serialNo;
    String brand;
    double rating;
    long price;

    public ComputerDevice() {
    }

    ComputerDevice(int serialNo, String brand, double rating, long price) {
        this.serialNo = serialNo;
        this.brand = brand;
        this.rating = rating;
        this.price = price;
    }
}
