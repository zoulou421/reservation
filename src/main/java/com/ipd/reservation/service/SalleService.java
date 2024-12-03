package com.ipd.reservation.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.mapstruct.factory.Mappers;

import com.ipd.reservation.dao.ISalle;
import com.ipd.reservation.dao.SalleImpl;
import com.ipd.reservation.dto.SalleDto;
import com.ipd.reservation.dto.SeanceDto;
import com.ipd.reservation.mappers.ISalleMapper;
import com.ipd.reservation.mappers.ISeanceMapper;
import com.ipd.reservation.util.HibernateUtil;

public class SalleService implements ISalleService {

    private final ISalle salleDao;
    private final ISeanceMapper seanceMapper;
    private final ISalleMapper salleMapper;

    // Manual instantiation of dependencies in the constructor
    public SalleService() {
        // Initialize the EntityManager and DAO
        EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        this.salleDao = new SalleImpl(entityManager);

        // Check if the salleDao is instantiated correctly
        if (salleDao == null) {
            throw new IllegalArgumentException("salleDao cannot be null");
        }

        // Initialize mappers
        this.salleMapper = Mappers.getMapper(ISalleMapper.class);
        this.seanceMapper = Mappers.getMapper(ISeanceMapper.class);
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

    @Override
    public boolean saveSalle(SalleDto salleDto) {
        return salleDao.save(salleMapper.toEntity(salleDto));
    }

    @Override
    public boolean deleteSalle(long id) {
        return salleDao.deletion(id);
    }

    @Override
    public boolean updateSalle(SalleDto salleDto) {
        return salleDao.update(salleMapper.toEntity(salleDto));
    }

    @Override
    public List<SalleDto> listSalle() {
        return salleMapper.toDto(salleDao.getAllSalle());
    }

	@Override
	public SalleDto getSalleById(long id) {
		return salleMapper.toDto(salleDao.getSalleById(id));
	}

   
}
