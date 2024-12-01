package com.ipd.reservation.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



public class SeanceDto implements Serializable{
	
	private Long idDto;
  
    private Date dateSeanceDto;

    private int dureeDto; // en minutes
    
    private String typeDto;
    
    private List<ReservationDto> reservationsDto;
    

    private SalleDto salleDto;


	public SeanceDto() {
		super();
	}


	public SeanceDto(Long idDto, Date dateSeanceDto, int dureeDto, String typeDto, List<ReservationDto> reservationsDto,
			SalleDto salleDto) {
		super();
		this.idDto = idDto;
		this.dateSeanceDto = dateSeanceDto;
		this.dureeDto = dureeDto;
		this.typeDto = typeDto;
		this.reservationsDto = reservationsDto;
		this.salleDto = salleDto;
	}


	public Long getIdDto() {
		return idDto;
	}


	public void setIdDto(Long idDto) {
		this.idDto = idDto;
	}


	public Date getDateSeanceDto() {
		return dateSeanceDto;
	}


	public void setDateSeanceDto(Date dateSeanceDto) {
		this.dateSeanceDto = dateSeanceDto;
	}


	public int getDureeDto() {
		return dureeDto;
	}


	public void setDureeDto(int dureeDto) {
		this.dureeDto = dureeDto;
	}


	public String getTypeDto() {
		return typeDto;
	}


	public void setTypeDto(String typeDto) {
		this.typeDto = typeDto;
	}


	public List<ReservationDto> getReservationsDto() {
		return reservationsDto;
	}


	public void setReservationsDto(List<ReservationDto> reservationsDto) {
		this.reservationsDto = reservationsDto;
	}


	public SalleDto getSalleDto() {
		return salleDto;
	}


	public void setSalleDto(SalleDto salleDto) {
		this.salleDto = salleDto;
	}


	
}
