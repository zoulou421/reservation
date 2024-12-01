package com.ipd.reservation.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.ipd.reservation.dao.AppRoleDao;
import com.ipd.reservation.dao.IAppRole;
import com.ipd.reservation.dto.AppRoleDto;
import com.ipd.reservation.entities.AppRole;
import com.ipd.reservation.mappers.AppRoleMapper;
import com.ipd.reservation.util.HibernateUtil;


public class AppRoleService implements IAppRoleService<AppRoleDto> {

    private final IAppRole appRoleDao;
    private final AppRoleMapper appRoleMapper;

    // Constructor that takes an IAppRole instance
   /* public AppRoleService(IAppRole appRoleDao) {
        if (appRoleDao == null) {
            throw new IllegalArgumentException("AppRoleDao cannot be null");
        }
        this.appRoleDao = appRoleDao;
        this.appRoleMapper = new AppRoleMapper(); // You may want to use dependency injection here too
    }*/
    
    
    public AppRoleService() {
        EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
        this.appRoleDao = new AppRoleDao(entityManager);
        this.appRoleMapper = new AppRoleMapper(); // You may want to use dependency injection here too
    }


	@Override
    public AppRoleDto createRole(AppRoleDto appRoleDto) {
        // Convert AppRoleDto to AppRole entity
        AppRole roleEntity = appRoleMapper.toRoleEntity(appRoleDto);

        // Save the role entity
        appRoleDao.save(roleEntity);

        // Return the mapped AppRoleDto
        return appRoleMapper.toRoleDto(roleEntity);
    }

    @Override
    public AppRoleDto getRoleById(long id) {
        AppRole roleEntity = appRoleDao.getById(id);
        if (roleEntity == null) {
            throw new RuntimeException("Role not found with ID: " + id);
        }
        return appRoleMapper.toRoleDto(roleEntity);
    }

    /*@Override
    public List<AppRoleDto> listRoles() {
        List<AppRole> roleEntities = appRoleDao.list();
        return appRoleMapper.listRoleEntitiesToListRoleDtos(roleEntities);
    }*/
    
    @Override
    public List<AppRoleDto> listRoles() {
        List<AppRole> roleEntities = appRoleDao.list();
        return appRoleMapper.mapRoleEntitiesToRoleDtos(roleEntities); // Ensure this line is updated
    }


    @Override
    public boolean updateRole(AppRoleDto appRoleDto) {
        AppRole roleEntity = appRoleMapper.toRoleEntity(appRoleDto);
        if (appRoleDao.getById(appRoleDto.getId()) == null) {
            throw new RuntimeException("Role not found with ID: " + appRoleDto.getId());
        }
        return appRoleDao.update(roleEntity); // Assume update method returns boolean
    }

    @Override
    public boolean deleteRole(long id) {
        if (appRoleDao.getById(id) == null) {
            throw new RuntimeException("Role not found with ID: " + id);
        }
        return appRoleDao.delete(id); // Assume delete method returns boolean
    }

    @Override
    public AppRoleDto getRoleByName(String roleName) {
        AppRole roleEntity = appRoleDao.findByName(roleName);
        if (roleEntity == null) {
            throw new RuntimeException("Role not found with name: " + roleName);
        }
        return appRoleMapper.toRoleDto(roleEntity);
    }
}
