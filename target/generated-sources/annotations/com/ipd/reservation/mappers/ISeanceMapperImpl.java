package com.ipd.reservation.mappers;

import com.ipd.reservation.dto.ReservationDto;
import com.ipd.reservation.dto.SalleDto;
import com.ipd.reservation.dto.SeanceDto;
import com.ipd.reservation.entities.Reservation;
import com.ipd.reservation.entities.Salle;
import com.ipd.reservation.entities.Seance;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T09:45:49+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class ISeanceMapperImpl implements ISeanceMapper {

    @Override
    public SeanceDto toDto(Seance seance) {
        if ( seance == null ) {
            return null;
        }

        SeanceDto seanceDto = new SeanceDto();

        seanceDto.setIdDto( seance.getId() );
        seanceDto.setDateSeanceDto( seance.getDateSeance() );
        seanceDto.setDureeDto( seance.getDuree() );
        seanceDto.setTypeDto( seance.getType() );
        seanceDto.setReservationsDto( reservationListToReservationDtoList( seance.getReservations() ) );
        seanceDto.setSalleDto( salleToSalleDto( seance.getSalle() ) );

        return seanceDto;
    }

    @Override
    public Seance toEntity(SeanceDto seanceDto) {
        if ( seanceDto == null ) {
            return null;
        }

        Seance seance = new Seance();

        seance.setId( seanceDto.getIdDto() );
        seance.setDateSeance( seanceDto.getDateSeanceDto() );
        seance.setDuree( seanceDto.getDureeDto() );
        seance.setType( seanceDto.getTypeDto() );
        seance.setReservations( reservationDtoListToReservationList( seanceDto.getReservationsDto() ) );
        seance.setSalle( salleDtoToSalle( seanceDto.getSalleDto() ) );

        return seance;
    }

    @Override
    public List<SeanceDto> toDto(List<Seance> seances) {
        if ( seances == null ) {
            return null;
        }

        List<SeanceDto> list = new ArrayList<SeanceDto>( seances.size() );
        for ( Seance seance : seances ) {
            list.add( toDto( seance ) );
        }

        return list;
    }

    @Override
    public List<Seance> toEntity(List<SeanceDto> seanceDtos) {
        if ( seanceDtos == null ) {
            return null;
        }

        List<Seance> list = new ArrayList<Seance>( seanceDtos.size() );
        for ( SeanceDto seanceDto : seanceDtos ) {
            list.add( toEntity( seanceDto ) );
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

    protected List<ReservationDto> reservationListToReservationDtoList(List<Reservation> list) {
        if ( list == null ) {
            return null;
        }

        List<ReservationDto> list1 = new ArrayList<ReservationDto>( list.size() );
        for ( Reservation reservation : list ) {
            list1.add( reservationToReservationDto( reservation ) );
        }

        return list1;
    }

    protected SalleDto salleToSalleDto(Salle salle) {
        if ( salle == null ) {
            return null;
        }

        SalleDto salleDto = new SalleDto();

        return salleDto;
    }

    protected Reservation reservationDtoToReservation(ReservationDto reservationDto) {
        if ( reservationDto == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        return reservation;
    }

    protected List<Reservation> reservationDtoListToReservationList(List<ReservationDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Reservation> list1 = new ArrayList<Reservation>( list.size() );
        for ( ReservationDto reservationDto : list ) {
            list1.add( reservationDtoToReservation( reservationDto ) );
        }

        return list1;
    }

    protected Salle salleDtoToSalle(SalleDto salleDto) {
        if ( salleDto == null ) {
            return null;
        }

        Salle salle = new Salle();

        return salle;
    }
}
