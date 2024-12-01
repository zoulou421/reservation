package com.ipd.reservation.service;

import java.util.List;

public interface IAppRoleService<AppRoleDto> {

    public AppRoleDto createRole(AppRoleDto appRoleDto);

    public AppRoleDto getRoleById(long id);

    public List<AppRoleDto> listRoles();

    public boolean updateRole(AppRoleDto appRoleDto);

    public boolean deleteRole(long id);

    public AppRoleDto getRoleByName(String roleName);
}

