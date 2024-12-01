package com.ipd.reservation.dao;

import java.util.List;
import java.util.Optional;

import com.ipd.reservation.entities.AppRole;
import com.ipd.reservation.entities.AppUser;


public interface IAppUser extends Repository<AppUser> {
	public boolean save(AppUser appUser);

	public AppUser getById(long id);

	public boolean update(AppUser appUser);

	public boolean delete(long id);

	public List<AppUser> list();

	public AppUser findByEmail(String email);
	
	//Repository
	public AppUser get(long id, AppUser appUser);
	
	// New method to assign a role to a user
    public boolean assignRoleToUser(long userId, long roleId);
    
    public AppUser findUserByUsername(String username);
    
    public List<AppRole> getUserRolesByUsername(String username);
    
    public Optional<AppUser> findUserByEmail(String email);
}
