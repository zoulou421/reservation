package com.ipd.reservation.mappers;

import com.ipd.reservation.dto.PaiementDto;
import com.ipd.reservation.dto.ReservationDto;
import com.ipd.reservation.entities.Paiement;
import com.ipd.reservation.entities.Reservation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T09:45:48+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class IPaiementMapperImpl implements IPaiementMapper {

    @Override
    public PaiementDto toDto(Paiement paiement) {
        if ( paiement == null ) {
            return null;
        }

        PaiementDto paiementDto = new PaiementDto();

        paiementDto.setIdDto( paiement.getId() );
        paiementDto.setMontantDto( paiement.getMontant() );
        paiementDto.setDatePaiementDto( paiement.getDatePaiement() );
        paiementDto.setModeDto( paiement.getMode() );
        paiementDto.setReservationDto( reservationToReservationDto( paiement.getReservation() ) );

        return paiementDto;
    }

    @Override
    public Paiement toEntity(PaiementDto paiementDto) {
        if ( paiementDto == null ) {
            return null;
        }

        Paiement paiement = new Paiement();

        paiement.setId( paiementDto.getIdDto() );
        paiement.setMontant( paiementDto.getMontantDto() );
        paiement.setDatePaiement( paiementDto.getDatePaiementDto() );
        paiement.setMode( paiementDto.getModeDto() );
        paiement.setReservation( reservationDtoToReservation( paiementDto.getReservationDto() ) );

        return paiement;
    }

    @Override
    public List<PaiementDto> toDto(List<Paiement> paiements) {
        if ( paiements == null ) {
            return null;
        }

        List<PaiementDto> list = new ArrayList<PaiementDto>( paiements.size() );
        for ( Paiement paiement : paiements ) {
            list.add( toDto( paiement ) );
        }

        return list;
    }

    @Override
    public List<Paiement> toEntity(List<PaiementDto> paiementDtos) {
        if ( paiementDtos == null ) {
            return null;
        }

        List<Paiement> list = new ArrayList<Paiement>( paiementDtos.size() );
        for ( PaiementDto paiementDto : paiementDtos ) {
            list.add( toEntity( paiementDto ) );
        }

        return list;
    }

    protected ReservationDto reservationToReservationDto(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationDto reservationDto = new ReservationDto();

        return reservationDto;
    }

    protected Reservation reservationDtoToReservation(ReservationDto reservationDto) {
        if ( reservationDto == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        return reservation;
    }
}
