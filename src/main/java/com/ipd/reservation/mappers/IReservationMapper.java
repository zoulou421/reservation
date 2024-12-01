package com.ipd.reservation.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipd.reservation.dto.ReservationDto;
import com.ipd.reservation.entities.Reservation;

@Mapper(componentModel = "default") // Using default for manual instantiation
public interface IReservationMapper {

    // Mapping from Reservation entity to ReservationDto
    @Mapping(source = "id", target = "idDto")
    @Mapping(source = "dateReservation", target = "dateReservationDto")
    @Mapping(source = "status", target = "statusDto")
    @Mapping(source = "notifications", target = "notificationsDto")  // Ensure there is a NotificationMapper
    @Mapping(source = "seance", target = "seanceDto")  // Ensure there is a SeanceMapper
    @Mapping(source = "paiement", target = "paiementDto")  // Ensure there is a PaiementMapper
    @Mapping(source = "customerName", target = "customerNameDto")
    @Mapping(source = "phoneNumber", target = "phoneNumberDto")  // Mapping phoneNumber
    @Mapping(source = "email", target = "emailDto")  // Mapping email
    @Mapping(source = "numberOfGuests", target = "numberOfGuestsDto")  // Mapping numberOfGuests
    ReservationDto toDto(Reservation reservation);

    // Mapping from ReservationDto to Reservation entity
    @Mapping(source = "idDto", target = "id")
    @Mapping(source = "dateReservationDto", target = "dateReservation")
    @Mapping(source = "statusDto", target = "status")
    @Mapping(source = "notificationsDto", target = "notifications")  // Ensure there is a NotificationMapper
    @Mapping(source = "seanceDto", target = "seance")  // Ensure there is a SeanceMapper
    @Mapping(source = "paiementDto", target = "paiement")  // Ensure there is a PaiementMapper
    @Mapping(source = "customerNameDto", target = "customerName")
    @Mapping(source = "phoneNumberDto", target = "phoneNumber")  // Mapping phoneNumber
    @Mapping(source = "emailDto", target = "email")  // Mapping email
    @Mapping(source = "numberOfGuestsDto", target = "numberOfGuests")  // Mapping numberOfGuests
    Reservation toEntity(ReservationDto reservationDto);

    // Mapping from a list of Reservation entities to a list of ReservationDtos
    List<ReservationDto> toDto(List<Reservation> reservations);

    // Mapping from a list of ReservationDtos to a list of Reservation entities
    List<Reservation> toEntity(List<ReservationDto> reservationDtos);
}
