package com.ipd.reservation.dao;

import java.util.List;

import com.ipd.reservation.entities.Notifications;
import com.ipd.reservation.entities.Paiement;
import com.ipd.reservation.entities.Reservation;



public interface IReservation extends Repository<Reservation>{

	public boolean isValid();
	public String getFormattedDate();
	public void addNotification(Notifications notification);//Method to add a notification to the reservation
	public boolean isExpired();
	public double getTotalPaid();
	public void updateStatus(String newStatus);
	public void linkPayment(Paiement paiement);
	public List<Notifications> getUnreadNotifications();
	public boolean isPaid();
	public Reservation cloneReservation();
	
	public void addReservation(Reservation reservation);

	public void updateReservation(Reservation reservation); // Add this method

	public void deleteReservation(long id,Reservation reservation);
	public void deleteReservationById(long id);

	public Reservation getReservationById(long id,Reservation reservation);

    public List<Reservation> getAllReservations();
    
    public Reservation get(long id); 
}
