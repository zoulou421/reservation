package com.ipd.reservation.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.mapstruct.factory.Mappers;

import com.ipd.reservation.dao.IReservation;
import com.ipd.reservation.dao.ReservationImpl;
import com.ipd.reservation.dto.NotificationsDto;
import com.ipd.reservation.dto.PaiementDto;
import com.ipd.reservation.dto.ReservationDto;
import com.ipd.reservation.mappers.INotificationsMapper;
import com.ipd.reservation.mappers.IPaiementMapper;
import com.ipd.reservation.mappers.IReservationMapper;
import com.ipd.reservation.util.HibernateUtil;

public class ReservationService implements IReservationService {

    private IReservation reservationDao;
    private final IPaiementMapper paiementMapper;
    private final IReservationMapper reservationMapper;
    private final INotificationsMapper notificationsMapper;

    // Manual instantiation of dependencies in the constructor
    public ReservationService() {
        // Manual instantiation of the DAO and mappers
        EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        this.reservationDao = new ReservationImpl(entityManager);

        // Check if the reservationDao is instantiated correctly
        if (reservationDao == null) {
            throw new IllegalArgumentException("reservationDao cannot be null");
        }

        // Instantiate the mappers
        this.paiementMapper = Mappers.getMapper(IPaiementMapper.class);
        this.reservationMapper = Mappers.getMapper(IReservationMapper.class);
        this.notificationsMapper = Mappers.getMapper(INotificationsMapper.class);
    }

	@Override
	public boolean isValid() {
		return reservationDao.isValid();
	}

	@Override
	public String getFormattedDate() {
		return reservationDao.getFormattedDate();
	}

	@Override
	public void addNotificationDto(NotificationsDto notificationDto) {
		reservationDao.addNotification(notificationsMapper.toEntity(notificationDto));
	}

	@Override
	public boolean isExpired() {
		return reservationDao.isExpired();
	}

	@Override
	public double getTotalPaid() {
		return reservationDao.getTotalPaid();
	}

	@Override
	public void updateStatus(String newStatus) {
		reservationDao.updateStatus(newStatus);
	}

	@Override
	public void linkPayment(PaiementDto paiementDto) {
		reservationDao.linkPayment(paiementMapper.toEntity(paiementDto));
		
	}

	@Override
	public List<NotificationsDto> getUnreadNotifications() {
		return notificationsMapper.toDto(reservationDao.getUnreadNotifications());
	}

	@Override
	public boolean isPaid() {
		return reservationDao.isPaid();
	}

	@Override
	public ReservationDto cloneReservation() {
		return reservationMapper.toDto(reservationDao.cloneReservation());
	}

	@Override
	public void addReservation(ReservationDto reservationDto) {
		reservationDao.addReservation(reservationMapper.toEntity(reservationDto));
		
	}

	@Override
	public void updateReservation(ReservationDto reservationDto) {
		reservationDao.updateReservation(reservationMapper.toEntity(reservationDto));
	}

	@Override
	public void deleteReservation(long id, ReservationDto reservationDto) {
		reservationDao.deleteReservation(id, reservationMapper.toEntity(reservationDto));
	}

	@Override
	public ReservationDto getReservationByIdObject(long id, ReservationDto reservationDto) {
		return reservationMapper.toDto(reservationDao.getReservationById(id, reservationMapper.toEntity(reservationDto)));
	}

	@Override
	public List<ReservationDto> getAllReservations() {
		return reservationMapper.toDto(reservationDao.getAllReservations());
	}

	@Override
	public ReservationDto getReservationById(long id) {
		return reservationMapper.toDto(reservationDao.get(id));
	}

	@Override
	public void deleteReservationById(long id) {
		reservationDao.deleteReservationById(id);	
	}

	
}
