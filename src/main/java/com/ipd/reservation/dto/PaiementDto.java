package com.ipd.reservation.dto;

import java.io.Serializable;
import java.util.Date;

public class PaiementDto implements Serializable {

	private Long idDto;

    private double montantDto;

    private Date datePaiementDto;

    private String modeDto;
    
    private ReservationDto reservationDto;

	public PaiementDto() {
		super();
	}

	public PaiementDto(Long idDto, double montantDto, Date datePaiementDto, String modeDto,
			ReservationDto reservationDto) {
		super();
		this.idDto = idDto;
		this.montantDto = montantDto;
		this.datePaiementDto = datePaiementDto;
		this.modeDto = modeDto;
		this.reservationDto = reservationDto;
	}

	public Long getIdDto() {
		return idDto;
	}

	public void setIdDto(Long idDto) {
		this.idDto = idDto;
	}

	public double getMontantDto() {
		return montantDto;
	}

	public void setMontantDto(double montantDto) {
		this.montantDto = montantDto;
	}

	public Date getDatePaiementDto() {
		return datePaiementDto;
	}

	public void setDatePaiementDto(Date datePaiementDto) {
		this.datePaiementDto = datePaiementDto;
	}

	public String getModeDto() {
		return modeDto;
	}

	public void setModeDto(String modeDto) {
		this.modeDto = modeDto;
	}

	public ReservationDto getReservationDto() {
		return reservationDto;
	}

	public void setReservationDto(ReservationDto reservationDto) {
		this.reservationDto = reservationDto;
	}
}
