package com.ipd.reservation.service;

import java.util.Date;
import java.util.List;

import org.mapstruct.factory.Mappers;

import com.ipd.reservation.dao.INotifications;
import com.ipd.reservation.dto.NotificationsDto;
import com.ipd.reservation.dto.ReservationDto;
import com.ipd.reservation.mappers.INotificationsMapper;
import com.ipd.reservation.mappers.IReservationMapper;

public class NotificationsService implements INotificationsService{

	
	private final INotifications notificationsDao;
	private final IReservationMapper reservationMapper;
	private final INotificationsMapper notificationsMapper;
	
	
	public NotificationsService(INotifications notificationsDao) {
        if (notificationsDao == null) {
            throw new IllegalArgumentException("notificationsDao cannot be null");
        }
        
        this.notificationsDao = notificationsDao;
        notificationsMapper = Mappers.getMapper(INotificationsMapper.class);
        reservationMapper = Mappers.getMapper(IReservationMapper.class);
    }

	@Override
	public NotificationsDto createNotification(String message, Date dateNotification,
			boolean readNotif,ReservationDto reservationDto) {
		NotificationsDto nd = notificationsMapper
				.toDto(notificationsDao.createNotification(message, dateNotification,
						readNotif, reservationMapper.toEntity(reservationDto)));

		return nd;
	}

	@Override
	public List<NotificationsDto> getNotificationsByReservation(long reservationId) {
		return null;
	}

	@Override
	public NotificationsDto getNotification(long id) {
		return notificationsMapper.toDto(notificationsDao.getNotification(id));
	}

	@Override
	public void deleteNotification(long id) {
		notificationsDao.deleteNotification(id);
		
	}

	@Override
	public void markAsRead(long id) {
		notificationsDao.markAsRead(id);
	}

	@Override
	public void sendNotification(NotificationsDto notificationDto, String email) {
		notificationsDao.sendNotification(notificationsMapper.toEntity(notificationDto), email);
		
	}

	@Override
	public List<NotificationsDto> filterNotifications(Date fromDate, Date toDate, String type) {
		return notificationsMapper
				.toDto(notificationsDao.filterNotifications
						(fromDate, toDate, type));
	}

}
