package com.pet.businessdomain.personservice.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.pet.businessdomain.personservice.entities.CharacterExpenses;
import com.pet.businessdomain.personservice.entities.enumentities.Enum;
import lombok.Data;

@Data
public class PersonDto {

    private Long id;
    private Long uid;

    // ===== IDENTIDAD =====
    private String fullName;
    private Integer age;
    private String birthDate;
    private String city;

    private Enum.Gender gender;

    private LocalDateTime createdAt;

    // ===== SITUACIÓN VITAL =====
    private Enum.CurrentSituation currentSituation;
    private Enum.FamilySituation familySituation;
    private List<String> familyExpectations;

    // ===== EDUCACIÓN =====
    private Enum.EducationLevel educationLevel;
    private String educationField;
    private String educationSpecialization;

    private Enum.AcademicPerformance academicPerformance;
    private List<String> educationGaps;

    // ===== PROFESIONAL / FUTURO =====
    private Enum.CareerInterest careerInterest;
    private Enum.Aspiration aspiration;
    private Enum.AvailableTime availableTime;

    private List<String> learningResources;
    private List<String> technicalSkills;
    private List<String> strengths;

    // ===== ECONOMÍA =====
    private Enum.EconomicSupport economicSupport;
    private Double monthlyIncome;
    private List<String> incomeSources;
    private Double savings;
    private Double debts;

    private CharacterExpenses expenses;

    // ===== RETOS, APOYO Y METAS =====
    private List<String> challenges;
    private List<String> supportSystem;

    private List<String> longTermGoals;
    private List<String> shortTermGoals;

    private Integer academicXp;
    private Integer workXp;
    private Enum.ProgressLevel academicLevel;
    private Enum.ProgressLevel workLevel;

}
