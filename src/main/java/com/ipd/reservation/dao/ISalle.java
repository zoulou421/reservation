package com.ipd.reservation.dao;

import java.util.List;

import com.ipd.reservation.entities.Reservation;
import com.ipd.reservation.entities.Salle;
import com.ipd.reservation.entities.Seance;



public interface ISalle extends Repository<Salle>{
	
	 public boolean isFull();
	 public boolean canAccommodate(int numberOfPeople);
	 public int getAvailableCapacity();
	 public void addSeance(Seance seance);
	 public void removeSeance(Seance seance);
	 public List<Seance> getScheduledSeances();
	 public String getFormattedInfo();
	 public int countSeances();
	 public boolean hasSeances();
	 
	 public List<Salle> getAllSalle();
	 
	 public boolean deletion(long id);
	 public Salle getSalleById(long id);
	 

}
