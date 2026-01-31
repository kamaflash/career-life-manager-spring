package com.pet.businessdomain.formationservice.controller;

import com.pet.businessdomain.formationservice.dto.CharacterTrainingDto;
import com.pet.businessdomain.formationservice.entities.CharacterTraining;
import com.pet.businessdomain.formationservice.exceptions.BusinessRuleException;
import com.pet.businessdomain.formationservice.services.ICharacterTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trainer")
public class CharacterTrainingController {
@Autowired
    private ICharacterTrainingService characterTrainingService;

    @GetMapping("/character/{id}/trainings")
    public ResponseEntity<?> getCharacterTrainings(@PathVariable Long id) {
        List<CharacterTraining> trainings = characterTrainingService.getTrainingsForCharacter(id);
        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/character/{id}/completed")
    public ResponseEntity<?> getCompletedTrainings(@PathVariable Long id) {
        List<CharacterTraining> trainings = characterTrainingService.getCompletedTrainings(id);
        return ResponseEntity.ok(trainings);
    }

    @PostMapping("/subscribe")
    public ResponseEntity<CharacterTrainingDto> subscribeToCourse(
            @RequestBody CharacterTrainingDto dto) throws BusinessRuleException {

        CharacterTrainingDto subscribed = characterTrainingService.subscribeToCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(subscribed);
    }
}
