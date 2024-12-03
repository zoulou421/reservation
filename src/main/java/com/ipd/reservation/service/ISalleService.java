package com.ipd.reservation.service;

import java.util.List;

import com.ipd.reservation.dto.SalleDto;
import com.ipd.reservation.dto.SeanceDto;
import com.ipd.reservation.entities.Salle;

public interface ISalleService {
	 public boolean isFull();
	 public boolean canAccommodate(int numberOfPeople);
	 public int getAvailableCapacity();
	 public void addSeance(SeanceDto seanceDto);
	 public void removeSeance(SeanceDto seanceDto);
	 public List<SeanceDto> getScheduledSeances();
	 public String getFormattedInfo();
	 public int countSeances();
	 public boolean hasSeances();
	 
	public boolean saveSalle(SalleDto salleDto);
	public boolean deleteSalle(long id);
	public boolean updateSalle(SalleDto salleDto);
	public List<SalleDto> listSalle();
	public SalleDto getSalleById(long id);
}
