package com.rdinfro.task2;

public class Manager extends Employee {

    double bonus;

    public void setBonus(double b) {
        bonus = b;
    }

    @Override
    public void printDetails() {
        System.out.println("Manager details:");
        super.printDetails();
        System.out.println("Manager bonus: " + bonus);
    }
}
