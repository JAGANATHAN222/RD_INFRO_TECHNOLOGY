package com.rdinfro.task2;

public class SeniorManager extends Manager {

    double extraBonus;

    public void setExtraBonus(double e) {
        extraBonus = e;
    }

    @Override
    public void printDetails() {
        System.out.println("Senior Manager details:");
        super.printDetails();
        System.out.println("Senior Manager extra bonus: " + extraBonus);
    }
}
