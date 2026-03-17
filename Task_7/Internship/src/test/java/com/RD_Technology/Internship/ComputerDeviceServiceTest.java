package com.RD_Technology.Internship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ComputerDeviceServiceTest {

    @Mock
    private ComputerDeviceRepository repository;

    @InjectMocks
    private ComputerDeviceService service;

    private ComputerDevice device;

    @BeforeEach
    void setUp() {
        device = new ComputerDevice(101, "HP", 4.2, 65000L);
    }

    @Test
    void addDevice_shouldSave() {
        when(repository.save(any(ComputerDevice.class))).thenReturn(device);
        ComputerDevice saved = service.addDevice(device);
        assertNotNull(saved);
        assertEquals("HP", saved.getBrand());
        verify(repository, times(1)).save(device);
    }

    @Test
    void getAllDevices_shouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(device));
        List<ComputerDevice> list = service.getAllDevices();
        assertEquals(1, list.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getDeviceBySerialNo_shouldReturnDeviceOrNull() {
        when(repository.findById(101)).thenReturn(Optional.of(device));
        assertNotNull(service.getDeviceBySerialNo(101));

        when(repository.findById(999)).thenReturn(Optional.empty());
        assertNull(service.getDeviceBySerialNo(999));
    }

    @Test
    void updateDevice_shouldUpdateOrReturnNull() {
        when(repository.findById(101)).thenReturn(Optional.of(device));
        when(repository.save(any(ComputerDevice.class))).thenAnswer(inv -> inv.getArgument(0));

        ComputerDevice updated = new ComputerDevice(101, "Dell", 4.8, 70000L);
        ComputerDevice result = service.updateDevice(101, updated);

        assertNotNull(result);
        assertEquals("Dell", result.getBrand());
        assertEquals(4.8, result.getRating());
        assertEquals(70000L, result.getPrice());

        when(repository.findById(555)).thenReturn(Optional.empty());
        assertNull(service.updateDevice(555, updated));
    }

    @Test
    void deleteDevice_shouldDeleteOrReturnFalse() {
        when(repository.findById(101)).thenReturn(Optional.of(device));
        assertTrue(service.deleteDevice(101));
        verify(repository, times(1)).delete(device);

        when(repository.findById(999)).thenReturn(Optional.empty());
        assertFalse(service.deleteDevice(999));
    }
}

