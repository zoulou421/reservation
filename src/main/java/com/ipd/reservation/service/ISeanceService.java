package com.ipd.reservation.service;

import java.util.List;

import com.ipd.reservation.dto.SeanceDto;

public interface ISeanceService {
    public boolean isFull(long id);
    public boolean canAccommodate(long id, int numberOfPeople);
    public int getAvailableCapacity(long id);
    public void addSeance(SeanceDto seanceDto);
    public void removeSeance(SeanceDto seanceDto);
    public List<SeanceDto> getScheduledSeances();
    public String getFormattedInfo(long id);
    public int countSeances();
    public boolean hasSeances();
}
