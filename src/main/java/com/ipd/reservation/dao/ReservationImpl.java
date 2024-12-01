package com.ipd.reservation.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ipd.reservation.entities.Notifications;
import com.ipd.reservation.entities.Paiement;
import com.ipd.reservation.entities.Reservation;
import com.ipd.reservation.util.HibernateUtil;

public class ReservationImpl extends RepositoryImpl<Reservation> implements IReservation {

	private final EntityManager entityManager;

    // Constructor accepting EntityManager
    public ReservationImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
	@Override
    public boolean isValid() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Date currentDate = new Date();
            String hql = "FROM Reservation WHERE dateReservation > :currentDate";
            List<Reservation> validReservations = session.createQuery(hql)
                                                          .setParameter("currentDate", currentDate)
                                                          .list();
            return !validReservations.isEmpty();
        } catch (Exception e) {
            e.printStackTrace(); // Replace with proper logging
            return false;
        }
    }

    @Override
    public String getFormattedDate() {
        Reservation reservation = get(1, new Reservation());
        if (reservation != null && reservation.getDateReservation() != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            return formatter.format(reservation.getDateReservation());
        }
        return "No date available";
    }

    @Override
    public void addNotification(Notifications notification) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Reservation reservation = get(notification.getReservation().getId(), new Reservation());
            if (reservation != null) {
                List<Notifications> notifications = reservation.getNotifications();
                if (notifications == null) {
                    notifications = new ArrayList<>();
                }
                notifications.add(notification);
                reservation.setNotifications(notifications);
                update(reservation); // assuming update() works with session
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Replace with proper logging
        }
    }

    @Override
    public boolean isExpired() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Date currentDate = new Date();
            String hql = "FROM Reservation WHERE dateReservation < :currentDate";
            List<Reservation> expiredReservations = session.createQuery(hql)
                                                           .setParameter("currentDate", currentDate)
                                                           .list();
            return !expiredReservations.isEmpty();
        } catch (Exception e) {
            e.printStackTrace(); // Replace with proper logging
            return false;
        }
    }

    @Override
    public double getTotalPaid() {
        double totalPaid = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            for (Reservation reservation : list(new Reservation())) {
                if (reservation.getPaiement() != null) {
                    totalPaid += reservation.getPaiement().getMontant();
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Replace with proper logging
        }
        return totalPaid;
    }

    @Override
    public void updateStatus(String newStatus) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            for (Reservation reservation : list(new Reservation())) {
                reservation.setStatus(newStatus);
                update(reservation); // assuming update() works with session
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(); // Replace with proper logging
        }
    }

    @Override
    public void linkPayment(Paiement paiement) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Reservation reservation = get(paiement.getReservation().getId(), new Reservation());
            if (reservation != null) {
                reservation.setPaiement(paiement);
                update(reservation); // assuming update() works with session
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Replace with proper logging
        }
    }

    @Override
    public List<Notifications> getUnreadNotifications() {
        List<Notifications> unreadNotifications = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            for (Reservation reservation : list(new Reservation())) {
                for (Notifications notification : reservation.getNotifications()) {
                    if (!notification.isReadNotif()) {
                        unreadNotifications.add(notification);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Replace with proper logging
        }
        return unreadNotifications;
    }

    @Override
    public boolean isPaid() {
        for (Reservation reservation : list(new Reservation())) {
            if (reservation.getPaiement() == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Reservation cloneReservation() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Reservation original = get(1, new Reservation());
            if (original != null) {
                Reservation clone = new Reservation();
                clone.setDateReservation(original.getDateReservation());
                clone.setStatus(original.getStatus());
                clone.setSeance(original.getSeance());
                save(clone);
                return clone;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Replace with proper logging
        }
        return null;
    }

    @Override
    public void addReservation(Reservation reservation) {
        save(reservation); // assuming save works with session
    }

    @Override
    public void updateReservation(Reservation reservation) {
        update(reservation); // assuming update works with session
    }

    @Override
    public void deleteReservation(long id, Reservation reservation) {
        delete(id, reservation); // assuming delete works with session
    }

    @Override
    public Reservation getReservationById(long id, Reservation reservation) {
        return get(id, reservation); // assuming get works with session
    }

    @Override
    public List<Reservation> getAllReservations() {
        return list(new Reservation()); // assuming list works with session
    }

	@Override
	public Reservation get(long id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Reservation.class, id);
        } catch (Exception e) {
            // Log the exception (use a logger)
            e.printStackTrace();
            return null;
        }
	}

	@Override
	public void deleteReservationById(long id) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        // Start the transaction
	        Transaction transaction = session.beginTransaction();
	        
	        // Fetch the reservation entity by its ID
	        Reservation reservation = session.get(Reservation.class, id);
	        
	        // If the reservation exists, delete it
	        if (reservation != null) {
	            session.delete(reservation);
	            transaction.commit();
	            System.out.println("Reservation deleted successfully!");
	        } else {
	            System.out.println("Reservation with ID " + id + " not found.");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Optionally, log the error
	        System.out.println("Error deleting reservation: " + e.getMessage());
	    }
	}

}
