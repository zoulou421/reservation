package com.ipd.reservation.dao;

import java.util.List;

import com.ipd.reservation.entities.Seance;

public interface ISeance extends Repository<Seance> {
    
    // Checks if a specific seance is full based on the given ID
    public boolean isFull(long id);
    
    // Checks if a specific seance can accommodate a given number of people based on the given ID
    public boolean canAccommodate(long id, int numberOfPeople);
    
    // Retrieves the available capacity for a specific seance based on the given ID
    public int getAvailableCapacity(long id);
    
    // Adds a new seance to the system
    public void addSeance(Seance seance);
    
    // Removes a specific seance from the system
    public void removeSeance(Seance seance);
    
    // Retrieves a list of all scheduled seances
    public List<Seance> getScheduledSeances();
    
    // Retrieves formatted information about a specific seance based on the given ID
    public String getFormattedInfo(Long id);
    
    // Counts the total number of scheduled seances
    public int countSeances();
    
    // Checks if there are any scheduled seances
    public boolean hasSeances();
}
