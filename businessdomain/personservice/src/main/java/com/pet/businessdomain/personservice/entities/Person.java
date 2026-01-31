package com.pet.businessdomain.personservice.entities;

import com.pet.businessdomain.personservice.entities.enumentities.Enum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "persons")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;

    // ===== IDENTIDAD =====
    private String fullName;
    private Integer age;
    private String birthDate;
    private String city;

    @Enumerated(EnumType.STRING)
    private Enum.Gender gender;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    // ===== SITUACIÓN VITAL =====
    @Enumerated(EnumType.STRING)
    private Enum.CurrentSituation currentSituation;

    @Enumerated(EnumType.STRING)
    private Enum.FamilySituation familySituation;

    @ElementCollection
    @CollectionTable(
            name = "person_family_expectations",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "value")
    private List<String> familyExpectations;

    // ===== EDUCACIÓN =====
    @Enumerated(EnumType.STRING)
    private Enum.EducationLevel educationLevel;

    private String educationField;
    private String educationSpecialization;

    @Enumerated(EnumType.STRING)
    private Enum.AcademicPerformance academicPerformance;

    @ElementCollection
    @CollectionTable(
            name = "person_education_gaps",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "value")
    private List<String> educationGaps;

    // ===== PROFESIONAL / FUTURO =====
    @Enumerated(EnumType.STRING)
    private Enum.CareerInterest careerInterest;

    @Enumerated(EnumType.STRING)
    private Enum.Aspiration aspiration;

    @Enumerated(EnumType.STRING)
    private Enum.AvailableTime availableTime;

    @ElementCollection
    @CollectionTable(
            name = "person_learning_resources",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "value")
    private List<String> learningResources;

    @ElementCollection
    @CollectionTable(
            name = "person_technical_skills",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "value")
    private List<String> technicalSkills;

    @ElementCollection
    @CollectionTable(
            name = "person_strengths",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "value")
    private List<String> strengths;

    // ===== ECONOMÍA =====
    @Enumerated(EnumType.STRING)
    private Enum.EconomicSupport economicSupport;

    private Double monthlyIncome;

    @ElementCollection
    @CollectionTable(
            name = "person_income_sources",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "value")
    private List<String> incomeSources;

    private Double savings;
    private Double debts;

    @Embedded
    private CharacterExpenses expenses;

    // ===== RETOS, APOYO Y METAS =====
    @ElementCollection
    @CollectionTable(
            name = "person_challenges",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "value")
    private List<String> challenges;

    @ElementCollection
    @CollectionTable(
            name = "person_support_system",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "value")
    private List<String> supportSystem;

    @ElementCollection
    @CollectionTable(
            name = "person_long_term_goals",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "value")
    private List<String> longTermGoals;

    @ElementCollection
    @CollectionTable(
            name = "person_short_term_goals",
            joinColumns = @JoinColumn(name = "person_id")
    )
    @Column(name = "value")
    private List<String> shortTermGoals;

    // ===== EXPERIENCIA =====
    private Integer academicXp = 0;
    private Integer workXp = 0;

    // ===== NIVELES =====
    @Enumerated(EnumType.STRING)
    private Enum.ProgressLevel academicLevel;

    @Enumerated(EnumType.STRING)
    private Enum.ProgressLevel workLevel;
}
