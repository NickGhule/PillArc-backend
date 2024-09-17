package com.pillarc.pillarc_backend.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;

@Getter
@Entity
@Table(name = "medicine")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "prescription_required",nullable = false)
    private boolean prescriptionRequired;

    @Column(name = "type_of_sale")
    private String typeOfSale;

    @ManyToOne
    @JoinColumn(
        name = "manufacturer_id",
        nullable = false
    )
    private Manufacturer manufacturer;

    @Min(1)
    private double mrp;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(nullable = false, name = "habit_forming")
    private boolean habitForming;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "chemical_class_id")
    private ChemicalClass chemicalClass;

    @ManyToOne
    @JoinColumn(name = "therapeutic_class_id")
    private TherapeuticClass therapeuticClass;

    @ManyToOne
    @JoinColumn(name = "action_class_id")
    private ActionClass actionClass;

    @Column(name = "how_it_works", columnDefinition = "TEXT")
    private String howItWorks;


    @Column(name = "how_to_use", columnDefinition = "TEXT")
    private String howToUse;

    @ManyToMany
    @JoinTable(
        name = "medicine_uses",
        joinColumns = @JoinColumn(name = "medicine_id"),
        inverseJoinColumns = @JoinColumn(name = "use_id")
    )
    private Set<Use> uses;


    @ManyToMany
    @JoinTable(
        name = "medicine_side_effects",
        joinColumns = @JoinColumn(name = "medicine_id"),
        inverseJoinColumns = @JoinColumn(name = "side_effect_id")
    )
    private Set<SideEffect> sideEffects;

    @ManyToMany
    @JoinTable(
        name = "medicine_salts",
        joinColumns = @JoinColumn(name = "medicine_id"),
        inverseJoinColumns = @JoinColumn(name = "salt_id")
    )
    private Set<Salt> salts;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "alternative_medicines",
        joinColumns = @JoinColumn(name = "medicine_id"),
        inverseJoinColumns = @JoinColumn(name = "alternative_medicine_id")
    )
    private Set<Medicine> alternativeMedicines;

}
