package com.RD_Technology.Internship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerDeviceService {

    private static final Logger log = LoggerFactory.getLogger(ComputerDeviceService.class);

    private final ComputerDeviceRepository repository;

    public ComputerDeviceService(ComputerDeviceRepository repository) {
        this.repository = repository;
    }

    public ComputerDevice addDevice(ComputerDevice device) {
        if (device == null) {
            log.warn("addDevice called with null device");
            return null;
        }
        log.info("Adding computer device serialNo={}, brand={}", device.getSerialNo(), device.getBrand());
        return repository.save(device);
    }

    public List<ComputerDevice> getAllDevices() {
        log.debug("Fetching all computer devices");
        return repository.findAll();
    }

    public ComputerDevice getDeviceBySerialNo(Integer serialNo) {
        log.debug("Fetching computer device by serialNo={}", serialNo);
        return repository.findById(serialNo).orElse(null);
    }

    public ComputerDevice updateDevice(Integer serialNo, ComputerDevice updated) {
        if (updated == null) {
            log.warn("updateDevice called with null updated payload for serialNo={}", serialNo);
            return null;
        }
        ComputerDevice existing = repository.findById(serialNo).orElse(null);
        if (existing == null) {
            log.warn("Computer device not found for update serialNo={}", serialNo);
            return null;
        }
        log.info("Updating computer device serialNo={}", serialNo);
        existing.setBrand(updated.getBrand());
        existing.setRating(updated.getRating());
        existing.setPrice(updated.getPrice());
        return repository.save(existing);
    }

    public boolean deleteDevice(Integer serialNo) {
        ComputerDevice existing = repository.findById(serialNo).orElse(null);
        if (existing == null) {
            log.warn("Computer device not found for delete serialNo={}", serialNo);
            return false;
        }
        log.info("Deleting computer device serialNo={}", serialNo);
        repository.delete(existing);
        return true;
    }
}

