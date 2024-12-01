package com.ipd.reservation.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipd.reservation.dto.SalleDto;
import com.ipd.reservation.entities.Salle;


@Mapper(componentModel = "default") // Using default for manual instantiation
public interface ISalleMapper {

    // Mapping from Salle entity to SalleDto
    @Mapping(source = "id", target = "idDto")
    @Mapping(source = "nom", target = "nomDto")  // Adjusted from 'name' to 'nom' assuming French naming convention
    @Mapping(source = "capacite", target = "capaciteDto")  // Adjusted from 'capacity' to 'capacite'
    @Mapping(source = "seances", target = "seancesDto")  // Mapping the List<Seance> to List<SeanceDto>
    SalleDto toDto(Salle salle);

    // Mapping from SalleDto to Salle entity
    @Mapping(source = "idDto", target = "id")
    @Mapping(source = "nomDto", target = "nom")
    @Mapping(source = "capaciteDto", target = "capacite")
    @Mapping(source = "seancesDto", target = "seances")  // Mapping List<SeanceDto> back to List<Seance>
    Salle toEntity(SalleDto salleDto);

    // Mapping from a list of Salle entities to a list of SalleDtos
    List<SalleDto> toDto(List<Salle> salles);

    // Mapping from a list of SalleDtos to a list of Salle entities
    List<Salle> toEntity(List<SalleDto> salleDtos);
}
