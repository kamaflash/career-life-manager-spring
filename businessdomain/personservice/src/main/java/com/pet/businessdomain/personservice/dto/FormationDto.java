package com.pet.businessdomain.personservice.dto;

import com.pet.businessdomain.personservice.entities.enumentities.EnumFormation;
import lombok.Data;

import java.util.List;

@Data
public class FormationDto {

    private Long id;

    // ===== IDENTIDAD =====
    private String code;
    private String name;
    private String description;

    // ===== CLASIFICACIÓN =====
    private EnumFormation.CareerInterest category;
    private EnumFormation.TrainingType type;
    private EnumFormation.DifficultyLevel difficulty;

    // ===== REQUISITOS =====
    private EnumFormation.EducationLevel minEducationLevel;
    private Integer minAcademicLevel;
    private Integer minAcademicXp;
    private Integer maxAcademicXp;
    private List<EnumFormation.CareerInterest> allowedCareers;

    // ===== COSTE =====
    private Integer durationHours;
    private Double cost;
    private Integer effort;

    // ===== RESULTADO =====
    private Integer academicXpReward;
    private List<String> skillsUnlocked;

    private Boolean repeatable;
    private Boolean active;
}
