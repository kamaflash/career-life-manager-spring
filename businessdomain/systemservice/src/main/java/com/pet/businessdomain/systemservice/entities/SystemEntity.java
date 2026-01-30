package com.pet.businessdomain.systemservice.entities;

import com.pet.businessdomain.systemservice.entities.enumentities.Enum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "systems")
@Data
public class SystemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long uid;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime actualityAt = LocalDateTime.now();

}
