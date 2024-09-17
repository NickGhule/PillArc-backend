package com.pillarc.pillarc_backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.pillarc.pillarc_backend.DTO.MedicineDTO;
import com.pillarc.pillarc_backend.model.Manufacturer;
import com.pillarc.pillarc_backend.model.Medicine;
import com.pillarc.pillarc_backend.repository.ManufacturerRepository;
import com.pillarc.pillarc_backend.repository.MedicineRepository;

@Service
public class MedicineService {

    private MedicineRepository medicineRepository;

    private ManufacturerRepository manufacturerRepository;

    public MedicineService(MedicineRepository medicineRepository, ManufacturerRepository manufacturerRepository) {
        this.medicineRepository = medicineRepository;
        this.manufacturerRepository = manufacturerRepository;
    }



    public List<MedicineDTO> getMedicines(PageRequest page) {
        Page<Medicine> medicines = medicineRepository.findAll(page);
        return medicines.stream().map(medicine -> new MedicineDTO(medicine)).collect(Collectors.toList());
    }

    public MedicineDTO getMedicineById(Long id) {
        Medicine medicine = medicineRepository.findById(id).orElse(null);
        if (medicine == null) {
            return null;
        }
        MedicineDTO medicineDTO = new MedicineDTO(medicine);
        List<Medicine> alternativeMedicines = medicineRepository.getAlternativeMedicinesById(id);
        medicineDTO.setAlternativeMedicines(alternativeMedicines.stream().map(med -> new MedicineDTO(med)).collect(Collectors.toList()));
        return medicineDTO;
    }

    public List<MedicineDTO> getMedicinesByManufacturer(String manufacturer, PageRequest page) {
        Manufacturer manufacturerEntity = manufacturerRepository.findByName(manufacturer);
        Page<Medicine> medicines = medicineRepository.findByManufacturer(manufacturerEntity, page);
        return medicines.stream().map(medicine -> new MedicineDTO(medicine)).collect(Collectors.toList());
    }
}
