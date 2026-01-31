/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pet.businessdomain.personservice.services;

import com.pet.businessdomain.personservice.dto.PersonDto;
import com.pet.businessdomain.personservice.entities.Person;
import com.pet.businessdomain.personservice.exceptions.BusinessRuleException;
import java.util.List;
import java.util.Optional;


/**
 *
 * @author Pc
 */

public interface PersonService {
    List<PersonDto> getAllPersons();
    Optional<Person> getPersonById(Long id);
    Optional<Person> getPersonByUid(Long id);
    PersonDto createPerson(Person person);
    PersonDto updatePerson(Long id, PersonDto personDto) throws BusinessRuleException;
    void deletePerson(Long id);
    PersonDto getFull(Long uid) throws BusinessRuleException;
    List<PersonDto> getFullList(Long id) throws BusinessRuleException;

}
