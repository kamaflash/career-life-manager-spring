
package com.pet.businessdomain.personservice.controller;

import com.pet.businessdomain.personservice.dto.PersonDto;
import com.pet.businessdomain.personservice.dto.SystemDto;
import com.pet.businessdomain.personservice.entities.Person;
import com.pet.businessdomain.personservice.exceptions.BusinessRuleException;

import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.pet.businessdomain.personservice.transactions.BusinessTransactions;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.pet.businessdomain.personservice.mapper.PersonMapper;
import com.pet.businessdomain.personservice.repository.PersonRepository;
import com.pet.businessdomain.personservice.services.PersonService;

/**
 *
 * @author Pc
 */
@Slf4j
@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private static final int SIZE = 5;

    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BusinessTransactions businessTransactions;
    @GetMapping
    public ResponseEntity<?> getAllPersons(
            @RequestParam(name = "page",defaultValue = "0") int page) {
        int size = 0;
        if(page == 0) {
            size=9;
        } else {
            size = SIZE;
        }
        Pageable pageable = PageRequest.of(page, size);
        System.out.println("Pagina: " + page);
        Page<Person> personsPage = personRepository.findAll(pageable);

        if (personsPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No existen mascotas");
        }
        List<Person> petsList = personsPage.getContent();
        List<PersonDto> listPetDto = personMapper.toDtoList(petsList);
        Map<String, Object> response = new HashMap<>();
        response.put("persons", listPetDto);
        response.put("currentPage", personsPage.getNumber());
        response.put("totalItems", personsPage.getTotalElements());
        response.put("totalPages", personsPage.getTotalPages());
        response.put("pageSize", personsPage.getSize());
        response.put("hasNext", personsPage.hasNext());
        response.put("hasPrevious", personsPage.hasPrevious());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/uid/{id}")
    public PersonDto getPersonByUID(@PathVariable(name="id") Long id) throws BusinessRuleException {
        Optional<Person> optPerson = personService.getPersonByUid(id);
        log.info("IIIIIDDDD: "+id);
        log.info("optPerson: "+optPerson);
        Person person = personMapper.fromOptional(optPerson);
        PersonDto personDto = personMapper.toDto(person);
        log.info("personDto: "+personDto);

            return personDto;

    }

    @PostMapping
    public ResponseEntity<?> createPerson(@RequestBody Person person) throws BusinessRuleException, UnknownHostException, MessagingException {
        // Convertir DTO a Entidad

        PersonDto personDto = personService.createPerson(person);
        SystemDto systemDto = new SystemDto();
        systemDto.setUid(personDto.getUid());
        systemDto.setCreatedAt(personDto.getCreatedAt());
        systemDto.setActualityAt(personDto.getCreatedAt());
        systemDto = businessTransactions.setSystem(systemDto);
        log.info("HA PASADO: "+ systemDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(personDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable(name="id") Long id, @RequestBody PersonDto personDto) throws BusinessRuleException {
        if (personDto != null) {
            PersonDto dto = personService.updatePerson(id, personDto);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No es aceptable");
        }
    }



    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        personRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Hecho");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable(name = "id") Long id) {
        Optional<Person> find = personRepository.findById(id);
        if (find.isPresent()) {
            PersonDto personDto = personMapper.toDto(find.get());
            personRepository.delete(find.get());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(personDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No es aceptable");
        }
    }

}
