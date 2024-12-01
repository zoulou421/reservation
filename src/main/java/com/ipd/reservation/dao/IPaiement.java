package com.ipd.reservation.dao;

import com.ipd.reservation.entities.Paiement;
import com.ipd.reservation.entities.Reservation;

public interface IPaiement extends Repository<Paiement> {
	
	 // Method to check if the payment is valid
   boolean isValidPayment(Paiement paiement);
   
   // Method to get a summary of the payment
   String getPaymentSummary(Paiement paiement);
   
   // Method to update the payment details
   void updatePayment(Paiement paiement, double newMontant, String newMode);
   
   // Method to compare two payments (likely based on amount)
   int compareTo(Paiement paiement, Paiement other);
   
   // Method to format the payment date as a string
   String formatPaymentDate(Paiement paiement);
   
   // Method to check if the payment is past due
   boolean isPaymentPastDue(Paiement paiement);
   
   // Method to assign a reservation to a payment
   void assignReservation(Paiement paiement, Reservation newReservation);
   
   // Method to calculate the remaining balance after payment
   double calculateRemainingBalance(Paiement paiement, double totalAmount);
}
