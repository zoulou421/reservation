package com.ipd.reservation.service;

import java.util.List;

import com.ipd.reservation.dto.SeanceDto;

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
}
