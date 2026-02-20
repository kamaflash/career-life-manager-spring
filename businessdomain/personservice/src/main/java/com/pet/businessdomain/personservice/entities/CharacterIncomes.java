package com.pet.businessdomain.personservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterIncomes {
    private double nomina;
    private double educationIncome;
    private double otherIncome;
}
