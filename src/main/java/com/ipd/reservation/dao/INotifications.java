package com.ipd.reservation.dao;

import java.util.Date;
import java.util.List;

import com.ipd.reservation.entities.Notifications;
import com.ipd.reservation.entities.Reservation;

public interface INotifications extends Repository<Notifications>{

	public Notifications createNotification(String message, Date dateNotification,boolean readNotif, Reservation reservation);
	public List<Notifications> getNotificationsByReservation(Long reservationId);
	public Notifications getNotification(Long id);
	public void deleteNotification(Long id);
	public void markAsRead(Long id);
	public void sendNotification(Notifications notification, String email);
	public List<Notifications> filterNotifications(Date fromDate, Date toDate, String type);

}
