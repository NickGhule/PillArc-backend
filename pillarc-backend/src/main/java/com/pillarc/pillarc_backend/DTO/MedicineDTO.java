package com.pillarc.pillarc_backend.DTO;

import java.util.List;
import java.util.stream.Collectors;

import com.pillarc.pillarc_backend.model.Medicine;
import com.pillarc.pillarc_backend.model.Status;

import lombok.Data;

@Data
public class MedicineDTO {
    private Long id;
    private String name;
    private boolean isPrescriptionRequired;
    private String manufacturer;
    private List<String> salts;
    private List<String> sideEffects;
    private String typeOfSale;
    private double mrp;
    private Status status;
    private String imageUrl;
    private boolean isHabitForming;
    private String chemicalClass;
    private List<String> uses;
    private String therapeuticClass;
    private String howToUse;
    private String howItWorks;
    private List<MedicineDTO> alternativeMedicines;

    public MedicineDTO(Medicine medicine) {
        this.id = medicine.getId();
        this.name = medicine.getName();
        this.isPrescriptionRequired = medicine.isPrescriptionRequired();
        this.manufacturer = medicine.getManufacturer().getName();
        this.salts = medicine.getSalts().stream().map(salt -> salt.getName()).collect(Collectors.toList());
        this.sideEffects = medicine.getSideEffects().stream().map(sideEffect -> sideEffect.getName()).collect(Collectors.toList());
        this.typeOfSale = medicine.getTypeOfSale();
        this.mrp = medicine.getMrp();
        this.status = medicine.getStatus();
        this.imageUrl = medicine.getImageUrl();
        this.isHabitForming = medicine.isHabitForming();
        this.chemicalClass = medicine.getChemicalClass() != null ? medicine.getChemicalClass().getName() : null;
        this.uses = medicine.getUses().stream().map(use -> use.getName()).collect(Collectors.toList());
        this.therapeuticClass = medicine.getTherapeuticClass().getName();
        this.howToUse = medicine.getHowToUse();
        this.howItWorks = medicine.getHowItWorks();
    }

}
