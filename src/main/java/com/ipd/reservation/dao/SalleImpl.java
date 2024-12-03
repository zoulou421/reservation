package com.ipd.reservation.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ipd.reservation.entities.Reservation;
import com.ipd.reservation.entities.Salle;
import com.ipd.reservation.entities.Seance;
import com.ipd.reservation.util.HibernateUtil;

import java.util.List;

import javax.persistence.EntityManager;

public class SalleImpl extends RepositoryImpl<Salle> implements ISalle {
	
	
	private final EntityManager entityManager;

    // Constructor accepting EntityManager
    public SalleImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	

    @Override
    public boolean isFull() {
        // Check if the salle is full (assuming we compare available capacity with number of seances)
        Salle salle = getCurrentSalle();  // Fetch the current Salle entity
        return salle.getCapacite() == countSeances();  // Compare capacity with count of seances
    }

    @Override
    public boolean canAccommodate(int numberOfPeople) {
        // Check if the salle can accommodate the number of people
        Salle salle = getCurrentSalle();  // Fetch the current Salle entity
        return salle.getCapacite() - countSeances() >= numberOfPeople;  // Check if there is enough space
    }

    @Override
    public int getAvailableCapacity() {
        // Returns the available capacity of the salle (calculating it directly in the DAO)
        Salle salle = getCurrentSalle();  // Fetch the current Salle entity
        return salle.getCapacite() - countSeances();  // Calculate remaining capacity
    }

    @Override
    public void addSeance(Seance seance) {
        // Add a new seance to the salle
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Salle salle = getCurrentSalle();  // Get the current salle from the database
                salle.getSeances().add(seance);  // Add the new seance
                
                session.update(salle);  // Update the salle entity
                transaction.commit();  // Commit the transaction
            } catch (Exception e) {
                transaction.rollback();  // Rollback in case of error
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeSeance(Seance seance) {
        // Remove a seance from the salle
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Salle salle = getCurrentSalle();  // Get the current salle from the database
                salle.getSeances().remove(seance);  // Remove the seance

                session.update(salle);  // Update the salle entity
                transaction.commit();  // Commit the transaction
            } catch (Exception e) {
                transaction.rollback();  // Rollback in case of error
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Seance> getScheduledSeances() {
        // Return the list of scheduled seances for the salle
        Salle salle = getCurrentSalle();  // Get the current salle from the database
        return salle.getSeances();  // Return the list of seances
    }

    @Override
    public String getFormattedInfo() {
        // Return formatted information about the salle (e.g., name, capacity, etc.)
        Salle salle = getCurrentSalle();  // Get the current salle from the database
        return String.format("Salle ID: %d, Name: %s, Capacity: %d, Available Capacity: %d",
                salle.getId(), salle.getNom(), salle.getCapacite(), getAvailableCapacity());
    }

    @Override
    public int countSeances() {
        // Count the number of scheduled seances in the salle
        Salle salle = getCurrentSalle();  // Get the current salle from the database
        return salle.getSeances().size();  // Return the size of the seances list
    }

    @Override
    public boolean hasSeances() {
        // Check if the salle has scheduled seances
        Salle salle = getCurrentSalle();  // Get the current salle from the database
        return !salle.getSeances().isEmpty();  // Return true if there are seances
    }

    private Salle getCurrentSalle() {
        // Helper method to get the current Salle object
        // In a real implementation, you would likely get the Salle from the database based on some ID or context
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Salle.class, 1);  // Replace with actual logic to fetch the Salle
        }
    }


	@Override
	public boolean deletion(long id) {
		return delete(id, new Salle());// assuming list works with session
	}


	@Override
	public List<Salle> getAllSalle() {
		return list(new Salle()); // assuming list works with session
	}


	@Override
	public Salle getSalleById(long id) {
		return get(id, new Salle());
	}
}
