package com.pet.businessdomain.personservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterExpenses {
    private double housing;
    private double food;
    private double transport;
    private double education;
    private double other;
}
