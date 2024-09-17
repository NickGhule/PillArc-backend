package com.pillarc.pillarc_backend.repository;

import com.pillarc.pillarc_backend.model.ChemicalClass;
import com.pillarc.pillarc_backend.model.Manufacturer;
import com.pillarc.pillarc_backend.model.Medicine;
import com.pillarc.pillarc_backend.model.Salt;
import com.pillarc.pillarc_backend.model.Status;
import com.pillarc.pillarc_backend.model.Use;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {


    // Find medicines by name
    List<Medicine> findByName(String name);

    // Delete medicine by ID
    void deleteById(@NonNull Long id);

    // Find medicines by therapeutic class
    List<Medicine> findByTherapeuticClassId(Long therapeuticClassId);

    List<Medicine> findBySalts(Set<Salt> salts);

    List<Medicine> findByUses(Set<Use> uses);

    List<Medicine> findByChemicalClass(ChemicalClass chemicalClass);

    List<Medicine> findByStatus(Status status);


    // Find medicines that contain at least one salt from the given set
    @Query("SELECT m FROM Medicine m JOIN m.salts s WHERE s IN :salts")
    List<Medicine> findByAtLeastOneSalt(@Param("salts") Set<Salt> salts);


    // Declare the table alternative_medicines
    @Query("SELECT m FROM Medicine m JOIN m.alternativeMedicines a WHERE a.id = :id")
    List<Medicine> getAlternativeMedicinesById(@Param("id") Long id);

    Page<Medicine> findByManufacturer(Manufacturer manufacturerEntity, PageRequest page);

}