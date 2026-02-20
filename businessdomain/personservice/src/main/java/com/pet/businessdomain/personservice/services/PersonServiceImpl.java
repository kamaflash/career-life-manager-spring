/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pet.businessdomain.personservice.services;

import com.pet.businessdomain.personservice.dto.CharacterTrainingDto;
import com.pet.businessdomain.personservice.dto.FormationDto;
import com.pet.businessdomain.personservice.dto.PersonDto;
import com.pet.businessdomain.personservice.entities.Person;
import com.pet.businessdomain.personservice.entities.enumentities.EnumFormation;
import com.pet.businessdomain.personservice.exceptions.BusinessRuleException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pet.businessdomain.personservice.transactions.BusinessTransactions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.pet.businessdomain.personservice.mapper.PersonMapper;
import com.pet.businessdomain.personservice.repository.PersonRepository;

/**
 *
 * @author Pc
 */
@Service
@Slf4j
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private BusinessTransactions businessTransactions;
    // Add any required dependencies here (e.g., repositories, mappers)

    @Override
    public List<PersonDto> getAllPersons() {
        List<PersonDto> listPersonDto = personMapper.toDtoList(personRepository.findAll());

        return listPersonDto;
    }

    @Override
    public Optional<Person> getPersonById(Long id) {
        Optional<Person> opt = personRepository.findById(id);
        return opt;
    }

    @Override
    public Optional<Person> getPersonByUid(Long uid) {
        Optional<Person> opt = personRepository.findByUid(uid);
        return opt;

    }

    @Override
    public PersonDto getFull(Long id) throws BusinessRuleException  {
        // Implementation
        Optional<Person> optPerson = personRepository.findById(id);
        Person person = personMapper.fromOptional(optPerson);

        if (person != null) {
            PersonDto dto = personMapper.toDto(person);
            return dto;
        } else {
            BusinessRuleException businessRuleException = new BusinessRuleException("0002", "Error validación. Transacion no localizada. ", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        }
    }
    @Override
    public List<PersonDto> getFullList(Long id) throws BusinessRuleException  {
        // Implementation
        Optional<Person> optPerson = personRepository.findById(id);
        Person person = personMapper.fromOptional(optPerson);
        List<Person> listPerson = new ArrayList<>();
        listPerson.add(person);

        if (person != null) {
            List<PersonDto> dtoList = personMapper.toDtoList(listPerson);
            return dtoList;
        } else {
            BusinessRuleException businessRuleException = new BusinessRuleException("0002", "Error validación. Transacion no localizada. ", HttpStatus.PRECONDITION_FAILED);
            throw businessRuleException;
        }
    }

    @Override
    public PersonDto createPerson(Person person) {
        person.setCreatedAt(LocalDateTime.now());
        person = personRepository.save(person);
        return personMapper.toDto(person);
    }

    @Override
    public PersonDto updatePerson(Long id, PersonDto personDto) throws BusinessRuleException {

        log.info("Buscando Person con ID: {}", id);

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new BusinessRuleException(
                        "0002",
                        "Error validación. Persona no encontrada.",
                        HttpStatus.PRECONDITION_FAILED
                ));

        log.info("Persona encontrada: {}", person.getFullName());

        // ===== DATOS PERSONALES =====
        person.setFullName(personDto.getFullName());
        person.setAge(personDto.getAge());
        person.setCity(personDto.getCity());
        person.setCurrentSituation(personDto.getCurrentSituation());

        // ===== EDUCACIÓN =====
        person.setEducationLevel(personDto.getEducationLevel());
        person.setEducationField(personDto.getEducationField());
        person.setEducationSpecialization(personDto.getEducationSpecialization());
        person.setAcademicPerformance(personDto.getAcademicPerformance());
        person.setEducationGaps(personDto.getEducationGaps());

        // ===== FUTURO PROFESIONAL =====
        person.setCareerInterest(personDto.getCareerInterest());
        person.setAspiration(personDto.getAspiration());
        person.setAvailableTime(personDto.getAvailableTime());

        // ===== HABILIDADES =====
        person.setLearningResources(personDto.getLearningResources());
        person.setStrengths(personDto.getStrengths());
        person.setTechnicalSkills(personDto.getTechnicalSkills());

        // ===== ENTORNO FAMILIAR =====
        person.setFamilySituation(personDto.getFamilySituation());
        person.setFamilyExpectations(personDto.getFamilyExpectations());
        person.setSupportSystem(personDto.getSupportSystem());

        // ===== DIFICULTADES =====
        person.setChallenges(personDto.getChallenges());

        // ===== ECONOMÍA =====
        person.setEconomicSupport(personDto.getEconomicSupport());
        person.setMonthlyIncome(personDto.getMonthlyIncome());
        person.setIncomes(personDto.getIncomes());
        person.setSavings(personDto.getSavings());
        person.setDebts(personDto.getDebts());

        if (personDto.getExpenses() != null) {
            person.setExpenses(personDto.getExpenses());
        }

        try {
            Person saved = personRepository.save(person);
            log.info("Persona actualizada correctamente: {}", saved.getFullName());
            return personMapper.toDto(saved);

        } catch (Exception e) {
            log.error("Error al actualizar Person", e);
            throw new BusinessRuleException(
                    "0003",
                    "Error al guardar los cambios del usuario",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Override
    public void deletePerson(Long id) {
        // Implementation here
    }

    public CharacterTrainingDto formationCreate(FormationDto formationDto, CharacterTrainingDto trainingDto, Long id) {
        trainingDto.setCharacterId(id);
        trainingDto.setTrainingId(formationDto.getId());
        trainingDto.setTrainingName(formationDto.getName());
        trainingDto.setTrainingType(formationDto.getType());
        trainingDto.setTrainingDifficulty(formationDto.getDifficulty());
        trainingDto.setStatus(EnumFormation.TrainingStatus.IN_PROGRESS);
        trainingDto.setProgress(0);
        trainingDto.setInvestedHours(0);
        trainingDto.setStartedAt(LocalDateTime.now());
        trainingDto.setAcademicXpGained(formationDto.getAcademicXpReward());
        trainingDto.setApplied(formationDto.getActive());

        return trainingDto;
    }
}
