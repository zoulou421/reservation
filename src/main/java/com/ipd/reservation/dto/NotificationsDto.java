package com.ipd.reservation.dto;

import java.io.Serializable;
import java.util.Date;

public class NotificationsDto implements Serializable{
	 private Long idDto;
	 private String messageDto;
	 private Date dateNotificationDto;
	 private boolean readNotifDto=false;
	 private ReservationDto reservationDto;
	 
	public NotificationsDto() {
		super();
	}

	public NotificationsDto(Long idDto, String messageDto, Date dateNotificationDto, boolean readNotifDto,
			ReservationDto reservationDto) {
		super();
		this.idDto = idDto;
		this.messageDto = messageDto;
		this.dateNotificationDto = dateNotificationDto;
		this.readNotifDto = readNotifDto;
		this.reservationDto = reservationDto;
	}

	public Long getIdDto() {
		return idDto;
	}

	public void setIdDto(Long idDto) {
		this.idDto = idDto;
	}

	public String getMessageDto() {
		return messageDto;
	}

	public void setMessageDto(String messageDto) {
		this.messageDto = messageDto;
	}

	public Date getDateNotificationDto() {
		return dateNotificationDto;
	}

	public void setDateNotificationDto(Date dateNotificationDto) {
		this.dateNotificationDto = dateNotificationDto;
	}

	

	public boolean isReadNotifDto() {
		return readNotifDto;
	}

	public void setReadNotifDto(boolean readNotifDto) {
		this.readNotifDto = readNotifDto;
	}

	public ReservationDto getReservationDto() {
		return reservationDto;
	}

	public void setReservationDto(ReservationDto reservationDto) {
		this.reservationDto = reservationDto;
	}
 
	
}
