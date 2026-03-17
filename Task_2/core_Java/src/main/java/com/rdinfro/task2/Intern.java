package com.rdinfro.task2;

public class Intern extends Employee {

    int durationInMonths;

    public void setDuration(int months) {
        durationInMonths = months;
    }

    @Override
    public void printDetails() {
        System.out.println("Intern details:");
        super.printDetails();
        System.out.println("Intern duration (months): " + durationInMonths);
    }
}
