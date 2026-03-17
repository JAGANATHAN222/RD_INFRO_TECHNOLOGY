package com.RD_Technology.Internship;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerDeviceService {

    private final ComputerDeviceRepository repository;

    public ComputerDeviceService(ComputerDeviceRepository repository) {
        this.repository = repository;
    }

    public ComputerDevice addDevice(ComputerDevice device) {
        return repository.save(device);
    }

    public List<ComputerDevice> getAllDevices() {
        return repository.findAll();
    }

    public ComputerDevice getDeviceBySerialNo(Integer serialNo) {
        return repository.findById(serialNo).orElse(null);
    }

    public ComputerDevice updateDevice(Integer serialNo, ComputerDevice updated) {
        ComputerDevice existing = repository.findById(serialNo).orElse(null);
        if (existing == null) {
            return null;
        }
        existing.setBrand(updated.getBrand());
        existing.setRating(updated.getRating());
        existing.setPrice(updated.getPrice());
        return repository.save(existing);
    }

    public boolean deleteDevice(Integer serialNo) {
        ComputerDevice existing = repository.findById(serialNo).orElse(null);
        if (existing == null) {
            return false;
        }
        repository.delete(existing);
        return true;
    }
}

