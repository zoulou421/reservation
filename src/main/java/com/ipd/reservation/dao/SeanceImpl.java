package com.ipd.reservation.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ipd.reservation.entities.Seance;

public class SeanceImpl extends RepositoryImpl<Seance> implements ISeance {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean isFull(long id) {
        // Find the Seance by ID
        Seance seance = entityManager.find(Seance.class, id);
        if (seance != null && seance.getSalle() != null) {
            int reservedSeats = seance.getReservations().size(); // Count the reserved seats
            int totalCapacity = seance.getSalle().getCapacite(); // Get the capacity of the salle
            return reservedSeats >= totalCapacity; // If reserved seats are equal to or greater than capacity, it's full
        }
        return false;
    }

    @Override
    public boolean canAccommodate(long id, int numberOfPeople) {
        // Find the Seance by ID
        Seance seance = entityManager.find(Seance.class, id);
        if (seance != null && seance.getSalle() != null) {
            int availableCapacity = getAvailableCapacity(seance); // Get the available capacity
            return numberOfPeople <= availableCapacity; // Check if the seance can accommodate the given number of people
        }
        return false;
    }

    @Override
    public int getAvailableCapacity(long id) {
        // Find the Seance by ID
        Seance seance = entityManager.find(Seance.class, id);
        if (seance != null && seance.getSalle() != null) {
            return getAvailableCapacity(seance); // Calculate available capacity
        }
        return 0;
    }

    private int getAvailableCapacity(Seance seance) {
        // Calculate available capacity by subtracting reserved seats from total capacity
        int reservedSeats = seance.getReservations().size();
        int totalCapacity = seance.getSalle().getCapacite();
        return totalCapacity - reservedSeats; // Return available seats
    }

    @Override
    public void addSeance(Seance seance) {
        // Persist the new Seance entity
        entityManager.persist(seance); // Use persist for adding new entities
    }

    @Override
    public void removeSeance(Seance seance) {
        // Remove the Seance entity
        if (seance != null && entityManager.contains(seance)) {
            entityManager.remove(seance); // Remove entity from database
        }
    }

    @Override
    public List<Seance> getScheduledSeances() {
        // Retrieve all Seances
        Query query = entityManager.createQuery("SELECT s FROM Seance s");
        return query.getResultList(); // Return list of all Seances
    }

    @Override
    public String getFormattedInfo(Long id) {
        // Get formatted info about the Seance by ID
        Seance seance = entityManager.find(Seance.class, id);
        if (seance != null) {
            return "Seance: " + seance.getType() + " on " + seance.getDateSeance();
        }
        return "Seance not found"; // Return a message if the Seance is not found
    }

    @Override
    public int countSeances() {
        // Count total Seances
        Query query = entityManager.createQuery("SELECT COUNT(s) FROM Seance s");
        return ((Long) query.getSingleResult()).intValue(); // Return count of Seances
    }

    @Override
    public boolean hasSeances() {
        // Check if there are any scheduled Seances
        return countSeances() > 0; // Return true if there are Seances, false otherwise
    }
}
