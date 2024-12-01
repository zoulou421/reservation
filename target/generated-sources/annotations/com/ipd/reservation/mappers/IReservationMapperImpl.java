package com.ipd.reservation.mappers;

import com.ipd.reservation.dto.NotificationsDto;
import com.ipd.reservation.dto.PaiementDto;
import com.ipd.reservation.dto.ReservationDto;
import com.ipd.reservation.dto.SeanceDto;
import com.ipd.reservation.entities.Notifications;
import com.ipd.reservation.entities.Paiement;
import com.ipd.reservation.entities.Reservation;
import com.ipd.reservation.entities.Seance;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T09:45:49+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class IReservationMapperImpl implements IReservationMapper {

    @Override
    public ReservationDto toDto(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationDto reservationDto = new ReservationDto();

        reservationDto.setIdDto( reservation.getId() );
        reservationDto.setDateReservationDto( reservation.getDateReservation() );
        reservationDto.setStatusDto( reservation.getStatus() );
        reservationDto.setNotificationsDto( notificationsListToNotificationsDtoList( reservation.getNotifications() ) );
        reservationDto.setSeanceDto( seanceToSeanceDto( reservation.getSeance() ) );
        reservationDto.setPaiementDto( paiementToPaiementDto( reservation.getPaiement() ) );
        reservationDto.setCustomerNameDto( reservation.getCustomerName() );
        reservationDto.setPhoneNumberDto( reservation.getPhoneNumber() );
        reservationDto.setEmailDto( reservation.getEmail() );
        reservationDto.setNumberOfGuestsDto( reservation.getNumberOfGuests() );

        return reservationDto;
    }

    @Override
    public Reservation toEntity(ReservationDto reservationDto) {
        if ( reservationDto == null ) {
            return null;
        }

        Reservation reservation = new Reservation();

        reservation.setId( reservationDto.getIdDto() );
        reservation.setDateReservation( reservationDto.getDateReservationDto() );
        reservation.setStatus( reservationDto.getStatusDto() );
        reservation.setNotifications( notificationsDtoListToNotificationsList( reservationDto.getNotificationsDto() ) );
        reservation.setSeance( seanceDtoToSeance( reservationDto.getSeanceDto() ) );
        reservation.setPaiement( paiementDtoToPaiement( reservationDto.getPaiementDto() ) );
        reservation.setCustomerName( reservationDto.getCustomerNameDto() );
        reservation.setPhoneNumber( reservationDto.getPhoneNumberDto() );
        reservation.setEmail( reservationDto.getEmailDto() );
        reservation.setNumberOfGuests( reservationDto.getNumberOfGuestsDto() );

        return reservation;
    }

    @Override
    public List<ReservationDto> toDto(List<Reservation> reservations) {
        if ( reservations == null ) {
            return null;
        }

        List<ReservationDto> list = new ArrayList<ReservationDto>( reservations.size() );
        for ( Reservation reservation : reservations ) {
            list.add( toDto( reservation ) );
        }

        return list;
    }

    @Override
    public List<Reservation> toEntity(List<ReservationDto> reservationDtos) {
        if ( reservationDtos == null ) {
            return null;
        }

        List<Reservation> list = new ArrayList<Reservation>( reservationDtos.size() );
        for ( ReservationDto reservationDto : reservationDtos ) {
            list.add( toEntity( reservationDto ) );
        }

        return list;
    }

    protected NotificationsDto notificationsToNotificationsDto(Notifications notifications) {
        if ( notifications == null ) {
            return null;
        }

        NotificationsDto notificationsDto = new NotificationsDto();

        return notificationsDto;
    }

    protected List<NotificationsDto> notificationsListToNotificationsDtoList(List<Notifications> list) {
        if ( list == null ) {
            return null;
        }

        List<NotificationsDto> list1 = new ArrayList<NotificationsDto>( list.size() );
        for ( Notifications notifications : list ) {
            list1.add( notificationsToNotificationsDto( notifications ) );
        }

        return list1;
    }

    protected SeanceDto seanceToSeanceDto(Seance seance) {
        if ( seance == null ) {
            return null;
        }

        SeanceDto seanceDto = new SeanceDto();

        return seanceDto;
    }

    protected PaiementDto paiementToPaiementDto(Paiement paiement) {
        if ( paiement == null ) {
            return null;
        }

        PaiementDto paiementDto = new PaiementDto();

        return paiementDto;
    }

    protected Notifications notificationsDtoToNotifications(NotificationsDto notificationsDto) {
        if ( notificationsDto == null ) {
            return null;
        }

        Notifications notifications = new Notifications();

        return notifications;
    }

    protected List<Notifications> notificationsDtoListToNotificationsList(List<NotificationsDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Notifications> list1 = new ArrayList<Notifications>( list.size() );
        for ( NotificationsDto notificationsDto : list ) {
            list1.add( notificationsDtoToNotifications( notificationsDto ) );
        }

        return list1;
    }

    protected Seance seanceDtoToSeance(SeanceDto seanceDto) {
        if ( seanceDto == null ) {
            return null;
        }

        Seance seance = new Seance();

        return seance;
    }

    protected Paiement paiementDtoToPaiement(PaiementDto paiementDto) {
        if ( paiementDto == null ) {
            return null;
        }

        Paiement paiement = new Paiement();

        return paiement;
    }
}
