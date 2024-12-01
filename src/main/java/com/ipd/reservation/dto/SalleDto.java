package com.ipd.reservation.dto;

import java.io.Serializable;
import java.util.List;

public class SalleDto implements Serializable{
	private Long idDto;
	
    private String nomDto;
    
    private int capaciteDto;
    
    private List<SeanceDto> seancesDto;

	public SalleDto() {
		super();
	}

	public SalleDto(Long idDto, String nomDto, int capaciteDto, List<SeanceDto> seancesDto) {
		super();
		this.idDto = idDto;
		this.nomDto = nomDto;
		this.capaciteDto = capaciteDto;
		this.seancesDto = seancesDto;
	}

	public Long getIdDto() {
		return idDto;
	}

	public void setIdDto(Long idDto) {
		this.idDto = idDto;
	}

	public String getNomDto() {
		return nomDto;
	}

	public void setNomDto(String nomDto) {
		this.nomDto = nomDto;
	}

	public int getCapaciteDto() {
		return capaciteDto;
	}

	public void setCapaciteDto(int capaciteDto) {
		this.capaciteDto = capaciteDto;
	}

	public List<SeanceDto> getSeancesDto() {
		return seancesDto;
	}

	public void setSeancesDto(List<SeanceDto> seancesDto) {
		this.seancesDto = seancesDto;
	}

}
