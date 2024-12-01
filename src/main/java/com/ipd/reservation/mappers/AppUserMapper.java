package com.ipd.reservation.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ipd.reservation.dto.AppRoleDto;
import com.ipd.reservation.dto.AppUserDto;
import com.ipd.reservation.entities.AppRole;
import com.ipd.reservation.entities.AppUser;

public class AppUserMapper {

	 /**
     * Map a list of AppUser entities to a list of AppUserDto.
     *
     * @param users List of AppUser entities
     * @return List of AppUserDto
     */
    public static List<AppUserDto> listUserEntitiesToListUserDtos(List<AppUser> users) {
        return Optional.ofNullable(users)
                       .orElse(List.of()) // Return empty list if users is null
                       .stream()
                       .map(AppUserMapper::toUserDto)
                       .collect(Collectors.toList());
    }

    /**
     * Map an AppUser entity to an AppUserDto.
     *
     * @param user AppUser entity
     * @return AppUserDto
     */
    public static AppUserDto toUserDto(AppUser user) {
        return Optional.ofNullable(user)
                       .map(u -> new AppUserDto(
                           u.getId(),
                           u.getUserName(),
                           u.getEmail(),
                           u.getPassword(),
                           listRoleEntitiesToListRoleDtos(u.getRoles())  // Convert roles
                       ))
                       .orElse(null);
    }

    /**
     * Map an AppUserDto to an AppUser entity.
     *
     * @param userDto AppUserDto
     * @return AppUser entity
     */
    public static AppUser toUserEntity(AppUserDto userDto) {
        return Optional.ofNullable(userDto)
                       .map(dto -> new AppUser(
                           dto.getId(),
                           dto.getUserName(),
                           dto.getEmail(),
                           dto.getPassword(),
                           listRoleDtosToListRoleEntities(dto.getRoles())  // Convert roles
                       ))
                       .orElse(null);
    }

    /**
     * Map a list of AppRole entities to a list of AppRoleDto.
     *
     * @param roles List of AppRole entities
     * @return List of AppRoleDto
     */
    public static List<AppRoleDto> listRoleEntitiesToListRoleDtos(List<AppRole> roles) {
        return Optional.ofNullable(roles)
                       .orElse(List.of()) // Return empty list if roles is null
                       .stream()
                       .map(AppUserMapper::toRoleDto)
                       .collect(Collectors.toList());
    }

    /**
     * Map an AppRole entity to an AppRoleDto.
     *
     * @param role AppRole entity
     * @return AppRoleDto
     */
    public static AppRoleDto toRoleDto(AppRole role) {
        return Optional.ofNullable(role)
                       .map(r -> new AppRoleDto(r.getId(), r.getNom())) // Changed 'nom' to 'name'
                       .orElse(null);
    }

    /**
     * Map a list of AppRoleDto to a list of AppRole entities.
     *
     * @param roleDtos List of AppRoleDto
     * @return List of AppRole entities
     */
    public static List<AppRole> listRoleDtosToListRoleEntities(List<AppRoleDto> roleDtos) {
        return Optional.ofNullable(roleDtos)
                       .orElse(List.of()) // Return empty list if roleDtos is null
                       .stream()
                       .map(AppUserMapper::toRoleEntity)
                       .collect(Collectors.toList());
    }

    /**
     * Map an AppRoleDto to an AppRole entity.
     *
     * @param roleDto AppRoleDto
     * @return AppRole entity
     */
    public static AppRole toRoleEntity(AppRoleDto roleDto) {
        return Optional.ofNullable(roleDto)
                       .map(dto -> new AppRole(dto.getId(), dto.getNom(), null))  // Users are not set during this mapping
                       .orElse(null);
    }
    
    
}
