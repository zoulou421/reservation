package com.ipd.reservation.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ipd.reservation.dto.NotificationsDto;
import com.ipd.reservation.entities.Notifications;


@Mapper(componentModel = "default") // Using default for manual instantiation
public interface INotificationsMapper {

    // Mapping from Notifications entity to NotificationsDto
    @Mapping(source = "id", target = "idDto")
    @Mapping(source = "message", target = "messageDto")
    @Mapping(source = "dateNotification", target = "dateNotificationDto")
    @Mapping(source = "readNotif", target = "readNotifDto")
    @Mapping(source = "reservation", target = "reservationDto")  // Ensure reservationDto is mapped properly
    NotificationsDto toDto(Notifications notification);

    // Mapping from NotificationsDto to Notifications entity
    @Mapping(source = "idDto", target = "id")
    @Mapping(source = "messageDto", target = "message")
    @Mapping(source = "dateNotificationDto", target = "dateNotification")
    @Mapping(source = "readNotifDto", target = "readNotif")
    @Mapping(source = "reservationDto", target = "reservation")  // Ensure reservation is mapped properly
    Notifications toEntity(NotificationsDto notificationsDto);

    // Mapping from a list of Notifications entities to a list of NotificationsDto
    List<NotificationsDto> toDto(List<Notifications> notifications);

    // Mapping from a list of NotificationsDto to a list of Notifications entities
    List<Notifications> toEntity(List<NotificationsDto> notificationsDtos);
}
