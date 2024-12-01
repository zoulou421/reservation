package com.ipd.reservation.service;

import java.util.List;

import org.mapstruct.factory.Mappers;

import com.ipd.reservation.dao.ISeance;
import com.ipd.reservation.dto.SeanceDto;
import com.ipd.reservation.mappers.ISalleMapper;
import com.ipd.reservation.mappers.ISeanceMapper;

public class SeanceService implements ISeanceService{
	
	private final ISeance seanceDao;
	private final ISalleMapper salleMapper ;
	private final ISeanceMapper seanceMapper;
	
	public SeanceService(ISeance seanceDao) {
		if(seanceDao==null){
			throw new IllegalArgumentException("seanceDao cannot be null");
		}
		this.seanceDao = seanceDao;
		salleMapper = Mappers.getMapper(ISalleMapper.class);
		seanceMapper=Mappers.getMapper(ISeanceMapper.class);
	}

	@Override
	public boolean isFull(long id) {
		return seanceDao.isFull(id);
	}

	@Override
	public boolean canAccommodate(long id, int numberOfPeople) {
		return seanceDao.canAccommodate(id, numberOfPeople);
	}

	@Override
	public int getAvailableCapacity(long id) {
		return seanceDao.getAvailableCapacity(id);
	}

	@Override
	public void addSeance(SeanceDto seanceDto) {
		seanceDao.addSeance(seanceMapper.toEntity(seanceDto));
	}

	@Override
	public void removeSeance(SeanceDto seanceDto) {
		seanceDao.removeSeance(seanceMapper.toEntity(seanceDto));
	}

	@Override
	public List<SeanceDto> getScheduledSeances() {
		return seanceMapper.toDto(seanceDao.getScheduledSeances());
	}

	@Override
	public String getFormattedInfo(long id) {
		return seanceDao.getFormattedInfo(id);
	}

	@Override
	public int countSeances() {
		return seanceDao.countSeances();
	}

	@Override
	public boolean hasSeances() {
		return seanceDao.hasSeances();
	}

}
