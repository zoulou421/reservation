package com.ipd.reservation.service;

import java.util.List;

import org.mapstruct.factory.Mappers;

import com.ipd.reservation.dao.ISalle;
import com.ipd.reservation.dto.SeanceDto;
import com.ipd.reservation.mappers.ISalleMapper;
import com.ipd.reservation.mappers.ISeanceMapper;

public class SalleService implements ISalleService{

	private final ISalle salleDao;
	private final ISalleMapper salleMapper ;
	private final ISeanceMapper seanceMapper;
	
	public SalleService(ISalle salleDao) {
		if(salleDao==null) {
			throw new IllegalArgumentException("salleDao cannot be null");
		}
		this.salleDao = salleDao;
		salleMapper = Mappers.getMapper(ISalleMapper.class);
		seanceMapper=Mappers.getMapper(ISeanceMapper.class);
	}
	@Override
	public boolean isFull() {
		return salleDao.isFull();
	}

	@Override
	public boolean canAccommodate(int numberOfPeople) {
		return salleDao.canAccommodate(numberOfPeople);
	}

	@Override
	public int getAvailableCapacity() {
		return salleDao.getAvailableCapacity();
	}

	@Override
	public void addSeance(SeanceDto seanceDto) {
		salleDao.addSeance(seanceMapper.toEntity(seanceDto));
	}

	@Override
	public void removeSeance(SeanceDto seanceDto) {
		salleDao.removeSeance(seanceMapper.toEntity(seanceDto));
	}

	@Override
	public List<SeanceDto> getScheduledSeances() {
		return seanceMapper.toDto(salleDao.getScheduledSeances());
	}

	@Override
	public String getFormattedInfo() {
		return salleDao.getFormattedInfo();
	}

	@Override
	public int countSeances() {
		return salleDao.countSeances();
	}

	@Override
	public boolean hasSeances() {
		return salleDao.hasSeances();
	}

}
