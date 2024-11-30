package com.ipd.reservation.util;


import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.ipd.reservation.entities.AppRole;
import com.ipd.reservation.entities.AppUser;
import com.ipd.reservation.entities.Notifications;
import com.ipd.reservation.entities.Paiement;
import com.ipd.reservation.entities.Reservation;
import com.ipd.reservation.entities.Salle;
import com.ipd.reservation.entities.Seance;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                PropertiesReader reader = new PropertiesReader("database.properties");
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(AvailableSettings.URL, reader.getProperty("db.urlDev"));
                settings.put(AvailableSettings.USER, reader.getProperty("db.username"));
                settings.put(AvailableSettings.PASS, reader.getProperty("db.password"));
                settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                settings.put(AvailableSettings.HBM2DDL_AUTO, "update"); // Use 'update' instead of 'create' for production
                settings.put(AvailableSettings.SHOW_SQL, "true");
                settings.put(AvailableSettings.FORMAT_SQL, "true");
                settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(AppUser.class);
                configuration.addAnnotatedClass(AppRole.class);
                configuration.addAnnotatedClass(Reservation.class);
                configuration.addAnnotatedClass(Notifications.class);
                configuration.addAnnotatedClass(Paiement.class);
                configuration.addAnnotatedClass(Salle.class);
                configuration.addAnnotatedClass(Seance.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("SessionFactory successfully created.");

            } catch (Exception e) {
                System.err.println("SessionFactory initialization failed: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
