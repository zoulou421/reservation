package com.ipd.reservation.mappers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ipd.reservation.dto.AppRoleDto;
import com.ipd.reservation.entities.AppRole;

public class AppRoleMapper {

    /**
     * Map a list of AppRole entities to a list of AppRoleDto.
     *
     * @param roles List of AppRole entities
     * @return List of AppRoleDto
     */
    public static List<AppRoleDto> mapRoleEntitiesToRoleDtos(List<AppRole> roles) {
        return Optional.ofNullable(roles)
                       .orElse(List.of()) // Return empty list if roles is null
                       .stream()
                       .map(AppRoleMapper::toRoleDto)
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
                       .map(r -> new AppRoleDto(r.getId(), r.getNom()))
                       .orElse(null);
    }

    /**
     * Map a list of AppRoleDto to a list of AppRole entities.
     *
     * @param roleDtos List of AppRoleDto
     * @return List of AppRole entities
     */
    public static List<AppRole> mapRoleDtosToRoleEntities(List<AppRoleDto> roleDtos) {
        return Optional.ofNullable(roleDtos)
                       .orElse(List.of()) // Return empty list if roleDtos is null
                       .stream()
                       .map(AppRoleMapper::toRoleEntity)
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
                       .map(dto -> new AppRole(dto.getId(), dto.getNom(), null)) // Users not set during mapping
                       .orElse(null);
    }
}
