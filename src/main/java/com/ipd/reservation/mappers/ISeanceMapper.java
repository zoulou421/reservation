package com.ipd.reservation.mappers;

import java.util.Date;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipd.reservation.dto.SeanceDto;
import com.ipd.reservation.entities.Seance;


@Mapper(componentModel = "default") // Using default for manual instantiation
public interface ISeanceMapper {
	
    // Mapping from Seance entity to SeanceDto
    @Mapping(source = "id", target = "idDto")
    @Mapping(source = "dateSeance", target = "dateSeanceDto")
    @Mapping(source = "duree", target = "dureeDto")
    @Mapping(source = "type", target = "typeDto") 
    @Mapping(source = "reservations", target = "reservationsDto")
    @Mapping(source = "salle", target = "salleDto")

    SeanceDto toDto(Seance seance);

    // Mapping from SeanceDto to Seance entity
    @Mapping(source = "idDto", target = "id")
    @Mapping(source = "dateSeanceDto", target = "dateSeance")
    @Mapping(source = "dureeDto", target = "duree")
    @Mapping(source = "typeDto", target = "type") 
    @Mapping(source = "reservationsDto", target = "reservations")
    @Mapping(source = "salleDto", target = "salle")  // Ensure there is a LocationMapper if location is complex
    Seance toEntity(SeanceDto seanceDto);

    // Mapping from a list of Seance entities to a list of SeanceDtos
    List<SeanceDto> toDto(List<Seance> seances);

    // Mapping from a list of SeanceDtos to a list of Seance entities
    List<Seance> toEntity(List<SeanceDto> seanceDtos);
}
