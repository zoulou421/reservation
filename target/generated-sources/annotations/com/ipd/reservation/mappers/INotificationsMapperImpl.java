package com.ipd.reservation.mappers;

import com.ipd.reservation.dto.NotificationsDto;
import com.ipd.reservation.dto.ReservationDto;
import com.ipd.reservation.entities.Notifications;
import com.ipd.reservation.entities.Reservation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T09:45:48+0100",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class INotificationsMapperImpl implements INotificationsMapper {

    @Override
    public NotificationsDto toDto(Notifications notification) {
        if ( notification == null ) {
            return null;
        }

        NotificationsDto notificationsDto = new NotificationsDto();

        notificationsDto.setIdDto( notification.getId() );
        notificationsDto.setMessageDto( notification.getMessage() );
        notificationsDto.setDateNotificationDto( notification.getDateNotification() );
        notificationsDto.setReadNotifDto( notification.isReadNotif() );
        notificationsDto.setReservationDto( reservationToReservationDto( notification.getReservation() ) );

        return notificationsDto;
    }

    @Override
    public Notifications toEntity(NotificationsDto notificationsDto) {
        if ( notificationsDto == null ) {
            return null;
        }

        Notifications notifications = new Notifications();

        notifications.setId( notificationsDto.getIdDto() );
        notifications.setMessage( notificationsDto.getMessageDto() );
        notifications.setDateNotification( notificationsDto.getDateNotificationDto() );
        notifications.setReadNotif( notificationsDto.isReadNotifDto() );
        notifications.setReservation( reservationDtoToReservation( notificationsDto.getReservationDto() ) );

        return notifications;
    }

    @Override
    public List<NotificationsDto> toDto(List<Notifications> notifications) {
        if ( notifications == null ) {
            return null;
        }

        List<NotificationsDto> list = new ArrayList<NotificationsDto>( notifications.size() );
        for ( Notifications notifications1 : notifications ) {
            list.add( toDto( notifications1 ) );
        }

        return list;
    }

    @Override
    public List<Notifications> toEntity(List<NotificationsDto> notificationsDtos) {
        if ( notificationsDtos == null ) {
            return null;
        }

        List<Notifications> list = new ArrayList<Notifications>( notificationsDtos.size() );
        for ( NotificationsDto notificationsDto : notificationsDtos ) {
            list.add( toEntity( notificationsDto ) );
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
