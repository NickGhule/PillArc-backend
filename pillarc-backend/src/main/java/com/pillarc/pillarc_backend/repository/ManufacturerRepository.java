package com.pillarc.pillarc_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.pillarc.pillarc_backend.model.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {

    Manufacturer findByName(String name);
} 
