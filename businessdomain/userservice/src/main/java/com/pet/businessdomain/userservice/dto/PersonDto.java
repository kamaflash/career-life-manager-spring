/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet.businessdomain.personservice.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.pet.businessdomain.personservice.entities.CharacterExpenses;
import com.pet.businessdomain.personservice.entities.enumentities.Enum;
import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.Data;

/**
 *
 * @author Pc
 */
@Data
public class PersonDto {

    private Long id;
    private Long uid;
    // ===== IDENTIDAD =====
    private String fullName;
    private Integer age;
    private String city;
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

    // ===== RETOS Y APOYO =====
    private List<String> challenges;
    private List<String> supportSystem;

    // ===== CONFIGURACIÓN DE JUEGO =====
    private Enum.GameDifficulty gameDifficulty;
    private Enum.GameFocus gameFocus;


}
