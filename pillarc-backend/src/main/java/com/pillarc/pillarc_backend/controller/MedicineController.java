package com.pillarc.pillarc_backend.controller;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pillarc.pillarc_backend.DTO.MedicineDTO;
import com.pillarc.pillarc_backend.service.MedicineService;

@RestController
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/")
    public List<MedicineDTO> getMedicines(
            @RequestParam(name = "manufacturer", required = false) String manufacturer,
            @RequestParam(name = "page", defaultValue = "0") int page) {
        PageRequest pageable = PageRequest.of(page, 10);
        if (manufacturer != null) {
            return medicineService.getMedicinesByManufacturer(manufacturer, pageable);
        }
        return medicineService.getMedicines(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicineDTO> getMedicineById(@PathVariable("id") Long id) {
        MedicineDTO medicine = medicineService.getMedicineById(id);
        if (medicine == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medicine);
    }
}
