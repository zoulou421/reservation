package com.ipd.reservation.service;

import org.mapstruct.factory.Mappers;

import com.ipd.reservation.dao.IPaiement;
import com.ipd.reservation.dto.PaiementDto;
import com.ipd.reservation.dto.ReservationDto;
import com.ipd.reservation.mappers.IPaiementMapper;
import com.ipd.reservation.mappers.IReservationMapper;

public class PaiementService implements IPaiementService {

    private final IPaiement paiementDao;
    private static final IPaiementMapper paiementMapper = Mappers.getMapper(IPaiementMapper.class);
    private final IReservationMapper reservationMapper;

    // Constructor
    public PaiementService(IPaiement paiementDao) {
        if (paiementDao == null) {
            throw new IllegalArgumentException("paiementDao cannot be null");
        }
        this.paiementDao = paiementDao;
        this.reservationMapper = Mappers.getMapper(IReservationMapper.class);
    }

    @Override
    public boolean isValidPayment(PaiementDto paiementDto) {
        return paiementDao.isValidPayment(paiementMapper.toEntity(paiementDto));
    }

    @Override
    public String getPaymentSummary(PaiementDto paiementDto) {
        return paiementDao.getPaymentSummary(paiementMapper.toEntity(paiementDto));
    }

    @Override
    public void updatePayment(PaiementDto paiementDto, double newMontant, String newMode) {
        paiementDao.updatePayment(paiementMapper.toEntity(paiementDto), newMontant, newMode);
    }

    @Override
    public int compareTo(PaiementDto paiementDto, PaiementDto otherDto) {
        return paiementDao.compareTo(
            paiementMapper.toEntity(paiementDto), 
            paiementMapper.toEntity(otherDto)
        );
    }

    @Override
    public String formatPaymentDate(PaiementDto paiementDto) {
        return paiementDao.formatPaymentDate(paiementMapper.toEntity(paiementDto));
    }

    @Override
    public boolean isPaymentPastDue(PaiementDto paiementDto) {
        return paiementDao.isPaymentPastDue(paiementMapper.toEntity(paiementDto));
    }

    @Override
    public void assignReservation(PaiementDto paiementDto, ReservationDto newReservationDto) {
        paiementDao.assignReservation(
            paiementMapper.toEntity(paiementDto), 
            reservationMapper.toEntity(newReservationDto)
        );
    }

    @Override
    public double calculateRemainingBalance(PaiementDto paiementDto, double totalAmount) {
        return paiementDao.calculateRemainingBalance(
            paiementMapper.toEntity(paiementDto), 
            totalAmount
        );
    }
}
