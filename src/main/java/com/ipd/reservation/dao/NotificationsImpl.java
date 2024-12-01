package com.ipd.reservation.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ipd.reservation.entities.Notifications;
import com.ipd.reservation.entities.Reservation;
import com.ipd.reservation.util.HibernateUtil;

public class NotificationsImpl extends RepositoryImpl<Notifications> implements INotifications {

    @Override
    public Notifications createNotification(String message, Date dateNotification, boolean readNotif, Reservation reservation) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Notifications notification = new Notifications();
            notification.setMessage(message);
            notification.setDateNotification(dateNotification);
            notification.setReadNotif(readNotif);;
            notification.setReservation(reservation);
            session.save(notification);
            transaction.commit();
            return notification;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback transaction if any error occurs
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Notifications> getNotificationsByReservation(Long reservationId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Notifications> cq = cb.createQuery(Notifications.class);
            Root<Notifications> root = cq.from(Notifications.class);
            Predicate reservationPredicate = cb.equal(root.get("reservation").get("id"), reservationId);
            cq.select(root).where(reservationPredicate);
            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Notifications getNotification(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Notifications.class, id); // Use session to get the entity directly
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteNotification(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Notifications notification = session.get(Notifications.class, id);
            if (notification != null) {
                session.delete(notification); // Delete notification
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void markAsRead(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Notifications notification = session.get(Notifications.class, id);
            if (notification != null) {
                notification.setReadNotif(true); // Mark as read
                session.update(notification); // Update notification
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void sendNotification(Notifications notification, String email) {
        try {
            // Sending notification logic can be implemented here (e.g., integrating with email services)
            System.out.println("Sending notification to " + email + ": " + notification.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Notifications> filterNotifications(Date fromDate, Date toDate, String type) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Notifications> cq = cb.createQuery(Notifications.class);
            Root<Notifications> root = cq.from(Notifications.class);
            Predicate datePredicate = cb.between(root.get("dateNotification"), fromDate, toDate);
            Predicate typePredicate = cb.equal(root.get("type"), type);
            cq.select(root).where(cb.and(datePredicate, typePredicate));
            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
