package com.ipd.reservation.service;

import java.util.List;

import com.ipd.reservation.dto.NotificationsDto;
import com.ipd.reservation.dto.PaiementDto;
import com.ipd.reservation.dto.ReservationDto;

public interface IReservationService {

	public boolean isValid();
	public String getFormattedDate();
	public void addNotificationDto(NotificationsDto notificationDto);//Method to add a notification to the reservation
	public boolean isExpired();
	public double getTotalPaid();
	public void updateStatus(String newStatus);
	public void linkPayment(PaiementDto paiementDto);
	public List<NotificationsDto> getUnreadNotifications();
	public boolean isPaid();
	public ReservationDto cloneReservation();
	

	public void addReservation(ReservationDto reservationDto);

	public void updateReservation(ReservationDto reservationDto); // Add this method

	public void deleteReservation(long id,ReservationDto reservationDto);
	public void deleteReservationById(long id);

	public ReservationDto getReservationByIdObject(long id,ReservationDto reservationDto);

    public List<ReservationDto> getAllReservations();
    
    public ReservationDto getReservationById(long id);
}
