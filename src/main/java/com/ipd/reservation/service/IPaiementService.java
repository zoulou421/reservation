package com.ipd.reservation.service;

import com.ipd.reservation.dto.PaiementDto;
import com.ipd.reservation.dto.ReservationDto;

public interface IPaiementService {
	   boolean isValidPayment(PaiementDto paiementDto);
	   String getPaymentSummary(PaiementDto paiementDto);
	   void updatePayment(PaiementDto paiementDto, double newMontant, String newMode);
	   int compareTo(PaiementDto paiementDto, PaiementDto otherDto);
	   String formatPaymentDate(PaiementDto paiementDto);
	   boolean isPaymentPastDue(PaiementDto paiementDto);
	   void assignReservation(PaiementDto paiementDto, ReservationDto newReservationDto);
	   double calculateRemainingBalance(PaiementDto paiementDto, double totalAmount);
}
