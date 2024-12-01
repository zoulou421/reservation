package com.ipd.reservation.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="notifications")
public class Notifications implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNotification;
    
    private boolean readNotif;
    
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

	public Notifications() {
		super();
	}
	
	public Notifications(String message, Date dateNotification, boolean readreadNotif, Reservation reservation) {
		super();
		this.message = message;
		this.dateNotification = dateNotification;
		this.readNotif = readNotif;
		this.reservation = reservation;
	}

	public Notifications(String message, Date dateNotification, Reservation reservation) {
		super();
		this.message = message;
		this.dateNotification = dateNotification;
		this.reservation = reservation;
	}

	public Notifications(Long id, String message, Date dateNotification, boolean readNotif, Reservation reservation) {
		super();
		this.id = id;
		this.message = message;
		this.dateNotification = dateNotification;
		this.readNotif = readNotif;
		this.reservation = reservation;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateNotification() {
		return dateNotification;
	}

	public void setDateNotification(Date dateNotification) {
		this.dateNotification = dateNotification;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public boolean isReadNotif() {
		return readNotif;
	}

	public void setReadNotif(boolean readNotif) {
		this.readNotif = readNotif;
	}

	

}
