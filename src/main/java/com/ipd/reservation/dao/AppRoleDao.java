package com.ipd.reservation.dao;

import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.ipd.reservation.entities.AppRole;


public class AppRoleDao implements IAppRole {

    private final EntityManager entityManager;

    // Constructor to inject the EntityManager
    public AppRoleDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean save(AppRole appRole) {
        try {
            entityManager.getTransaction().begin(); // Start transaction
            entityManager.persist(appRole);
            entityManager.getTransaction().commit(); // Commit transaction
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Rollback on error
            }
            e.printStackTrace(); // Consider using a logging framework
            return false;
        }
    }

    @Override
    public AppRole getById(long id) {
        return entityManager.find(AppRole.class, id);
    }

    @Override
    public boolean update(AppRole appRole) {
        try {
            entityManager.getTransaction().begin(); // Start transaction
            entityManager.merge(appRole);
            entityManager.getTransaction().commit(); // Commit transaction
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Rollback on error
            }
            e.printStackTrace(); // Consider using a logging framework
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            entityManager.getTransaction().begin(); // Start transaction
            AppRole appRole = getById(id);
            if (appRole != null) {
                entityManager.remove(appRole);
                entityManager.getTransaction().commit(); // Commit transaction
                return true;
            }
            entityManager.getTransaction().rollback(); // Rollback if no entity found
            return false;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Rollback on error
            }
            e.printStackTrace(); // Consider using a logging framework
            return false;
        }
    }

    @Override
    public List<AppRole> list() {
        return entityManager.createQuery("SELECT r FROM AppRole r", AppRole.class).getResultList();
    }

    @Override
    public AppRole findByName(String roleName) {
        try {
            TypedQuery<AppRole> query = entityManager.createQuery("SELECT r FROM AppRole r WHERE r.nom = :roleName", AppRole.class);
            query.setParameter("roleName", roleName);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Handle case when no result is found
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logging framework
            return null;
        }
    }

    @Override
    public AppRole get(long id, AppRole appRole) {
        return getById(id); // Return the role by ID
    }

    @Override
    public boolean delete(long id, AppRole t) {
        return delete(id); // Delegate to the delete method with a single id
    }

    @Override
    public List<AppRole> list(AppRole t) {
        // Filter roles based on specific criteria from the AppRole instance `t`
        return entityManager.createQuery("SELECT r FROM AppRole r WHERE r.nom = :nom", AppRole.class)
                .setParameter("nom", t.getNom())
                .getResultList();
    }

}
