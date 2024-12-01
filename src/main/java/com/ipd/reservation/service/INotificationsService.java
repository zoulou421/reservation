package com.ipd.reservation.service;

import java.util.Date;
import java.util.List;

import com.ipd.reservation.dto.NotificationsDto;
import com.ipd.reservation.dto.ReservationDto;

public interface INotificationsService {
	public NotificationsDto createNotification(String message, Date dateNotification,boolean readNotif, ReservationDto reservationDto);
	public List<NotificationsDto> getNotificationsByReservation(long reservationId);
	public NotificationsDto getNotification(long id);
	public void deleteNotification(long id);
	public void markAsRead(long id);
	public void sendNotification(NotificationsDto notificationDto, String email);
	public List<NotificationsDto> filterNotifications(Date fromDate, Date toDate, String type);
}
