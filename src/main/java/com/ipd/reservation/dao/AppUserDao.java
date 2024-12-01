package com.ipd.reservation.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ipd.reservation.entities.AppRole;
import com.ipd.reservation.entities.AppUser;
import com.ipd.reservation.exceptions.UserNotFoundException;
import com.ipd.reservation.util.HibernateUtil;



public class AppUserDao extends RepositoryImpl<AppUser> implements IAppUser {

	private final EntityManager entityManager;

    // Constructor accepting EntityManager
    public AppUserDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public AppUser getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AppUser user = null;
        try {
            user = session.get(AppUser.class, id);
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public AppUser get(long id, AppUser appUser) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AppUser user = session.get(AppUser.class, id);
        session.close();
        return user;
    }

    @Override
    public boolean delete(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        boolean deleted = false;
        try {
            transaction = session.beginTransaction();
            AppUser user = session.get(AppUser.class, id);
            if (user != null) {
                session.delete(user);
                deleted = true;
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return deleted;
    }

    @Override
    public List<AppUser> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<AppUser> result = null;
        try {
            Query<AppUser> query = session.createQuery("FROM AppUser", AppUser.class);
            result = query.getResultList();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public AppUser findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        AppUser user = null;
        try {
            Query<AppUser> query = session.createQuery("FROM AppUser WHERE email = :email", AppUser.class);
            query.setParameter("email", email);
            user = query.uniqueResult(); // Will return null if no user found
        } finally {
            session.close();
        }
        return user;
    }

    
   

		@Override
	    public boolean assignRoleToUser(long userId, long roleId) {
	        EntityTransaction transaction = entityManager.getTransaction();
	        boolean assigned = false;
	        try {
	            transaction.begin();
	            AppUser user = entityManager.find(AppUser.class, userId);
	            AppRole role = entityManager.find(AppRole.class, roleId); // Fetch the role by roleId
	            
	            if (user != null && role != null) {
	                user.getRoles().add(role); // Assuming AppUser has a Set<AppRole> roles
	                entityManager.merge(user); // Update user with new role
	                assigned = true;
	            }
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction.isActive()) transaction.rollback();
	            e.printStackTrace();
	        }
	        return assigned;
	    }

		/*@Override
		public AppUser findUserByUsername(String username) {
			try {
				String str1="SELECT * FROM app_user WHERE username = 'admin1@gmail.com'";
				String str2="SELECT u FROM AppUser u WHERE u.userName = :username";

		        return entityManager.createQuery("SELECT * FROM AppUser WHERE username = 'admin1@gmail.com'", AppUser.class)
		                .setParameter("username", username)
		                .getSingleResult();
				// Au lieu de SELECT * FROM ...
				String hql = "FROM AppUser WHERE username = :username";
				Query<AppUser> query = session.createQuery(hql, AppUser.class);
				query.setParameter("username", username);
				AppUser user = query.uniqueResult();

		    } catch (NoResultException e) {
		        // Loggez l'erreur ou retournez null
		    	 e.printStackTrace();
		        return null;
		    }*/
		@Override
		public AppUser findUserByUsername(String username) {
		    Session session = HibernateUtil.getSessionFactory().openSession();
		    AppUser user = null;
		    try {
		        String hql = "FROM AppUser WHERE username = :username";
		        Query<AppUser> query = session.createQuery(hql, AppUser.class);
		        query.setParameter("username", username);
		        user = query.uniqueResult();
		    } catch (NoResultException e) {
		        e.printStackTrace(); // Log the error or handle as needed
		    } finally {
		        session.close(); // Ensure the session is closed
		    }
		    return user;
		}


		/*@Override
		public List<AppRole> getUserRolesByUsername(String username) {
			IAppUser appUserDao=new AppUserDao(entityManager);
			AppUser user = appUserDao.findUserByUsername(username);
		    if (user == null) {
		        // Gérer le cas où l'utilisateur n'existe pas
		        // Vous pouvez lancer une exception, ou retourner une liste vide, selon vos besoins
		        throw new UserNotFoundException("User with username " + username + " not found.");
		    }
		    return user.getRoles();
			
		}*/
		/*@Override
		public List<AppRole> getUserRolesByUsername(String username) {
		    AppUser user = this.findUserByUsername(username);
		    if (user == null) {
		        throw new UserNotFoundException("User with username " + username + " not found.");
		    }
		    return user.getRoles();
		}*/
		@Override
		public List<AppRole> getUserRolesByUsername(String username) {
		    AppUser user = Optional.ofNullable(this.findUserByUsername(username))
		                           .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found."));
		    return user.getRoles();
		}

		@Override
		public Optional<AppUser> findUserByEmail(String email) {
		    return entityManager.createQuery("SELECT u FROM AppUser u WHERE u.email = :email", AppUser.class)
		                        .setParameter("email", email)
		                        .getResultStream()
		                        .findFirst();
		}



    

}
