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

        // ===== EXPECTATIVAS FAMILIARES =====
        @ElementCollection(fetch = FetchType.LAZY)
        @CollectionTable(
                name = "person_family_expectations",
                joinColumns = @JoinColumn(name = "person_id")
        )
        @Column(name = "expectation")
        private List<String> familyExpectations;

        // ===== EDUCACIÓN =====
        @Enumerated(EnumType.STRING)
        private Enum.EducationLevel educationLevel;

        private String educationField;
        private String educationSpecialization;

        @Enumerated(EnumType.STRING)
        private Enum.AcademicPerformance academicPerformance;

        @ElementCollection(fetch = FetchType.LAZY)
        @CollectionTable(
                name = "person_education_gaps",
                joinColumns = @JoinColumn(name = "person_id")
        )
        @Column(name = "education_gap")
        private List<String> educationGaps;

        // ===== PROFESIONAL / FUTURO =====
        @Enumerated(EnumType.STRING)
        private Enum.CareerInterest careerInterest;

        @Enumerated(EnumType.STRING)
        private Enum.Aspiration aspiration;

        @Enumerated(EnumType.STRING)
        private Enum.AvailableTime availableTime;

        @ElementCollection(fetch = FetchType.LAZY)
        @CollectionTable(
                name = "person_learning_resources",
                joinColumns = @JoinColumn(name = "person_id")
        )
        @Column(name = "learning_resource")
        private List<String> learningResources;

        @ElementCollection(fetch = FetchType.LAZY)
        @CollectionTable(
                name = "person_technical_skills",
                joinColumns = @JoinColumn(name = "person_id")
        )
        @Column(name = "technical_skill")
        private List<String> technicalSkills;

        @ElementCollection(fetch = FetchType.LAZY)
        @CollectionTable(
                name = "person_strengths",
                joinColumns = @JoinColumn(name = "person_id")
        )
        @Column(name = "strength")
        private List<String> strengths;

        // ===== ECONOMÍA =====
        @Enumerated(EnumType.STRING)
        private Enum.EconomicSupport economicSupport;

        private Double monthlyIncome;

        @Embedded
        private CharacterIncomes incomes;

        private Double savings;
        private Double debts;

        @Embedded
        private CharacterExpenses expenses;

        // ===== RETOS, APOYO Y METAS =====
        @ElementCollection(fetch = FetchType.LAZY)
        @CollectionTable(
                name = "person_challenges",
                joinColumns = @JoinColumn(name = "person_id")
        )
        @Column(name = "challenge")
        private List<String> challenges;

        @ElementCollection(fetch = FetchType.LAZY)
        @CollectionTable(
                name = "person_support_system",
                joinColumns = @JoinColumn(name = "person_id")
        )
        @Column(name = "support_member")
        private List<String> supportSystem;

        @ElementCollection(fetch = FetchType.LAZY)
        @CollectionTable(
                name = "person_long_term_goals",
                joinColumns = @JoinColumn(name = "person_id")
        )
        @Column(name = "long_term_goal")
        private List<String> longTermGoals;

        @ElementCollection(fetch = FetchType.LAZY)
        @CollectionTable(
                name = "person_short_term_goals",
                joinColumns = @JoinColumn(name = "person_id")
        )
        @Column(name = "short_term_goal")
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
