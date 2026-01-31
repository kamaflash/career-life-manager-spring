package com.pet.businessdomain.personservice.mapper;

import com.pet.businessdomain.personservice.dto.PersonDto;
import com.pet.businessdomain.personservice.entities.Person;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersonMapper {

    // ===== ENTITY -> DTO (LECTURA) =====
    PersonDto toDto(Person person);

    List<PersonDto> toDtoList(List<Person> persons);

    // ===== DTO -> ENTITY (USO CONTROLADO) =====
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Person toEntity(PersonDto personDto);

    // ===== UPDATE PARCIAL (MUY IMPORTANTE) =====
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(PersonDto dto, @MappingTarget Person entity);

    // ===== OPTIONAL SUPPORT =====
    default Person fromOptional(Optional<Person> opt) {
        return opt.orElse(null);
    }

}
