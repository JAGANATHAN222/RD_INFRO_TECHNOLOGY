package com.RD_Technology.Internship;

import com.RD_Technology.Internship.ComputerDevice;
import com.RD_Technology.Internship.ComputerRepository;

public class HibernateDemoApp {
    public static void main(String[] args) {
        ComputerRepository repository = new ComputerRepository();
        ComputerDevice device = new ComputerDevice(101, "HP", 4.2, 65000);

        repository.create(device);
//        repository.update();
//        repository.updateRating();
//        repository.remove();
    }
}
