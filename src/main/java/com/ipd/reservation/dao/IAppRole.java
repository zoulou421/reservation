package com.ipd.reservation.dao;

import java.util.List;

import com.ipd.reservation.entities.AppRole;


public interface IAppRole extends Repository<AppRole> {

    // Save a new AppRole
    boolean save(AppRole appRole);

    // Get a role by its ID
    AppRole getById(long id);

    // Update an existing AppRole
    boolean update(AppRole appRole);

    // Delete an AppRole by its ID
    boolean delete(long id);

    // List all AppRoles
    List<AppRole> list();

    // Find an AppRole by its name (changed from email to name)
    AppRole findByName(String roleName);
    
    // Repository method (if needed; may not be required)
    AppRole get(long id, AppRole appRole);
    
 
}
