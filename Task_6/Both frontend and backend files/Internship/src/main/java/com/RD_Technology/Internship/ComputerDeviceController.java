package com.RD_Technology.Internship;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/computers")
public class ComputerDeviceController {

    private final ComputerDeviceService service;

    public ComputerDeviceController(ComputerDeviceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ComputerDevice> create(@RequestBody ComputerDevice device) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addDevice(device));
    }

    @GetMapping
    public List<ComputerDevice> getAll() {
        return service.getAllDevices();
    }

    @GetMapping("/{serialNo}")
    public ResponseEntity<ComputerDevice> getById(@PathVariable Integer serialNo) {
        ComputerDevice device = service.getDeviceBySerialNo(serialNo);
        if (device == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(device);
    }

    @PutMapping("/{serialNo}")
    public ResponseEntity<ComputerDevice> update(@PathVariable Integer serialNo, @RequestBody ComputerDevice updated) {
        ComputerDevice device = service.updateDevice(serialNo, updated);
        if (device == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(device);
    }

    @DeleteMapping("/{serialNo}")
    public ResponseEntity<Void> delete(@PathVariable Integer serialNo) {
        boolean deleted = service.deleteDevice(serialNo);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}

