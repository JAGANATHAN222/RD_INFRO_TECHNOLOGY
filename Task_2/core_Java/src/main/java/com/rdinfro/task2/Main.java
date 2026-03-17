package com.rdinfro.task2;

public class Main {

    public static void main(String[] args) {
        try {
            Employee emp = new Employee();
            emp.setData("Alice", 30000);

            Manager mgr = new Manager();
            mgr.setData("Bob", 50000);
            mgr.setBonus(5000);

            SeniorManager sm = new SeniorManager();
            sm.setData("Charlie", 70000);
            sm.setBonus(8000);
            sm.setExtraBonus(2000);

            Intern intern = new Intern();
            intern.setData("Dave", 10000);
            intern.setDuration(6);

            System.out.println("=== Employee ===");
            emp.printDetails();

            System.out.println("\n=== Manager (single inheritance) ===");
            mgr.printDetails();

            System.out.println("\n=== Senior Manager (multilevel inheritance) ===");
            sm.printDetails();

            System.out.println("\n=== Intern (hierarchical inheritance) ===");
            intern.printDetails();

            System.out.println("\n=== Trying invalid salary ===");
            Employee badEmp = new Employee();
            badEmp.setData("Eve", -5000);

        } catch (InvalidSalaryException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }
}
