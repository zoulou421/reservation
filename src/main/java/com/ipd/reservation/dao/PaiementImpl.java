package com.ipd.reservation.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ipd.reservation.entities.Paiement;
import com.ipd.reservation.entities.Reservation;
import com.ipd.reservation.util.HibernateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PaiementImpl extends RepositoryImpl<Paiement> implements IPaiement {

    @Override
    public boolean isValidPayment(Paiement paiement) {
        // Check if the payment amount is greater than zero and if the payment mode is valid (e.g., "CASH", "CARD", etc.)
        if (paiement.getMontant() <= 0) {
            return false;
        }

        // Assuming we have a list of valid payment modes
        String[] validModes = {"CASH", "CARD", "TRANSFER"};
        for (String mode : validModes) {
            if (paiement.getMode().equalsIgnoreCase(mode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getPaymentSummary(Paiement paiement) {
        // Return a summary string containing the payment details
        return String.format("Payment ID: %d, Amount: %.2f, Mode: %s, Date: %s",
                paiement.getId(), paiement.getMontant(), paiement.getMode(),
                new SimpleDateFormat("yyyy-MM-dd").format(paiement.getDatePaiement()));
    }

    @Override
    public void updatePayment(Paiement paiement, double newMontant, String newMode) {
        // Update the payment's montant and mode of payment
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Fetch the existing payment record
            Paiement existingPaiement = session.get(Paiement.class, paiement.getId());
            if (existingPaiement != null) {
                existingPaiement.setMontant(newMontant);
                existingPaiement.setMode(newMode);
                session.update(existingPaiement);
                transaction.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Paiement paiement, Paiement other) {
        // Compare two payments based on their amount (montant)
        if (paiement.getMontant() > other.getMontant()) {
            return 1;
        } else if (paiement.getMontant() < other.getMontant()) {
            return -1;
        }
        return 0;  // If amounts are equal
    }

    @Override
    public String formatPaymentDate(Paiement paiement) {
        // Format the payment date in a readable string format
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(paiement.getDatePaiement());
    }

    @Override
    public boolean isPaymentPastDue(Paiement paiement) {
        // Assume that a payment is past due if it is not done yet (date is in the past)
        Date currentDate = new Date();
        return paiement.getDatePaiement().before(currentDate) && paiement.getMontant() <= 0;
    }

    @Override
    public void assignReservation(Paiement paiement, Reservation newReservation) {
        // Assign the reservation to the payment (assumes the Paiement entity has a setReservation method)
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Paiement existingPaiement = session.get(Paiement.class, paiement.getId());
            if (existingPaiement != null) {
                existingPaiement.setReservation(newReservation);
                session.update(existingPaiement);
                transaction.commit();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calculateRemainingBalance(Paiement paiement, double totalAmount) {
        // Calculate the remaining balance to be paid based on the total amount and the current payment
        return totalAmount - paiement.getMontant();
    }
}
