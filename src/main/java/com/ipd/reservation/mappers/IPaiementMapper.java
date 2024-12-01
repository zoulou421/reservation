package com.ipd.reservation.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipd.reservation.dto.PaiementDto;
import com.ipd.reservation.entities.Paiement;


@Mapper(componentModel = "default") // Using default for manual instantiation
public interface IPaiementMapper {
    
    // Mapping from Paiement entity to PaiementDto
    @Mapping(source = "id", target = "idDto")
    @Mapping(source = "montant", target = "montantDto")
    @Mapping(source = "datePaiement", target = "datePaiementDto")
    @Mapping(source = "mode", target = "modeDto")
    @Mapping(source = "reservation", target = "reservationDto")  // Ensure reservationDto is mapped properly
    PaiementDto toDto(Paiement paiement);

    // Mapping from PaiementDto to Paiement entity
    @Mapping(source = "idDto", target = "id")
    @Mapping(source = "montantDto", target = "montant")
    @Mapping(source = "datePaiementDto", target = "datePaiement")
    @Mapping(source = "modeDto", target = "mode")
    @Mapping(source = "reservationDto", target = "reservation")  // Ensure reservation is mapped properly
    Paiement toEntity(PaiementDto paiementDto);

    // Mapping from a list of Paiement entities to a list of PaiementDto
    List<PaiementDto> toDto(List<Paiement> paiements);

    // Mapping from a list of PaiementDto to a list of Paiement entities
    List<Paiement> toEntity(List<PaiementDto> paiementDtos);
}
