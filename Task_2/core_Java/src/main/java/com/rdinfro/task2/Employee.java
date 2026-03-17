package com.rdinfro.task2;

public class Employee implements Printable, Payable {

    String name;
    double salary;

    public void setData(String n, double s) throws InvalidSalaryException {
        if (s < 0) {
            throw new InvalidSalaryException("Salary cannot be negative");
        }
        name = n;
        salary = s;
    }

    @Override
    public void printDetails() {
        System.out.println("Employee name: " + name);
        System.out.println("Employee salary: " + salary);
    }

    @Override
    public double getSalary() {
        return salary;
    }
}
