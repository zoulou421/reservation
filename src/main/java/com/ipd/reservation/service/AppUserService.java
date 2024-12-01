package com.ipd.reservation.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.mindrot.jbcrypt.BCrypt;

import com.ipd.reservation.dao.AppRoleDao;
import com.ipd.reservation.dao.AppUserDao;
import com.ipd.reservation.dao.IAppRole;
import com.ipd.reservation.dao.IAppUser;
import com.ipd.reservation.dto.AppRoleDto;
import com.ipd.reservation.dto.AppUserDto;
import com.ipd.reservation.entities.AppRole;
import com.ipd.reservation.entities.AppUser;
import com.ipd.reservation.exceptions.UserNotFoundException;
import com.ipd.reservation.mappers.AppUserMapper;
import com.ipd.reservation.util.HibernateUtil;

public class AppUserService implements IAppUserService {

	    private final IAppUser appUserDao;
	    private final IAppRole appRoleDao;
	    private final AppUserMapper appUserMapper;
	    

	    public AppUserService() {
            EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
            this.appUserDao = new AppUserDao(entityManager);
            this.appRoleDao = new AppRoleDao(entityManager);
            this.appUserMapper = new AppUserMapper();
  
		}

		/*public AppUserService(IAppUser appUserDao, IAppRole appRoleDao) {
	        if (appUserDao == null) {
	            throw new IllegalArgumentException("appUserDao cannot be null");
	        }
	        if (appRoleDao == null) {
	            throw new IllegalArgumentException("appRoleDao cannot be null");
	        }
	        this.appUserDao = appUserDao;
	        this.appRoleDao = appRoleDao;
	        this.appUserMapper = new AppUserMapper();
	    }*/

    @Override
    public AppUserDto register(AppUserDto userDto) {
        // Check if the user already exists by email
        if (appUserDao.findByEmail(userDto.getEmail()) != null) {
            throw new RuntimeException("User already exists with this email: " + userDto.getEmail());
        }

        // Convert UserDto to AppUser entity
        AppUser userEntity = appUserMapper.toUserEntity(userDto);

        // Hash the password before saving it to the database
        String hashedPassword = hashPassword(userDto.getPassword());
        userEntity.setPassword(hashedPassword);

        // Save the user entity
        appUserDao.save(userEntity);

        // Return the mapped UserDto
        return appUserMapper.toUserDto(userEntity);
    }

    // Method to hash the password using BCrypt
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public AppUserDto login(String email, String password) {
        // Fetch user entity by email
        AppUser userEntity = appUserDao.findByEmail(email);
        
        // Check if user exists
        if (userEntity == null) {
            throw new RuntimeException("User not found with this email: " + email);
        }
        
        // Verify password (use BCrypt to check the hashed password)
        if (!BCrypt.checkpw(password, userEntity.getPassword())) {
            throw new RuntimeException("Invalid password for email: " + email);
        }
        
        // Return the mapped user DTO if login is successful
        return appUserMapper.toUserDto(userEntity);
    }

    @Override
    public AppUserDto getUserById(long id) {
        AppUser userEntity = appUserDao.getById(id);
        return appUserMapper.toUserDto(userEntity);
    }

    @Override
    public List<AppUserDto> listUsers() {
        List<AppUser> userEntities = appUserDao.list();
        return appUserMapper.listUserEntitiesToListUserDtos(userEntities);
    }

    /*@Override
    public boolean updateUser(AppUserDto userDto) {
        AppUser userEntity = appUserMapper.toUserEntity(userDto);
        return appUserDao.update(userEntity); // Assume update method returns boolean
    }*/
    
    @Override
    public AppUserDto updateUser(AppUserDto userDto) {
        if (userDto == null) {
            throw new IllegalArgumentException("UserDto cannot be null");
        }

        // Retrieve the existing user from the database
        AppUser existingUser = appUserDao.getById(userDto.getId());
        if (existingUser == null) {
            throw new RuntimeException("User not found with ID: " + userDto.getId());
        }

        // Convert the DTO to an entity
        AppUser userEntity = appUserMapper.toUserEntity(userDto);

        // Check if the password has changed
        if (!BCrypt.checkpw(userDto.getPassword(), existingUser.getPassword())) {
            // If password is different, hash the new password
            String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
            userEntity.setPassword(hashedPassword);
        } else {
            // Keep the existing hashed password if it hasn’t changed
            userEntity.setPassword(existingUser.getPassword());
        }

        // Update the user entity in the database
        if (!appUserDao.update(userEntity)) {
            throw new RuntimeException("Failed to update user with ID: " + userDto.getId());
        }

        // Return the updated user data
        return appUserMapper.toUserDto(userEntity);
    }


    @Override
    public boolean deleteUser(long id) {
        return appUserDao.delete(id); // Assume delete method returns boolean
    }

    @Override
    public void assignRoleToUser(long userId, long roleId) {
        // Retrieve the user entity by userId
        AppUser userEntity = appUserDao.getById(userId);
        
        // Check if the user exists
        if (userEntity == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        
        // Retrieve the role entity by roleId
        AppRole roleEntity = appRoleDao.getById(roleId);
        
        // Check if the role exists
        if (roleEntity == null) {
            throw new RuntimeException("Role not found with ID: " + roleId);
        }

        // Check if the user already has this role to avoid duplicates
        if (!userEntity.getRoles().contains(roleEntity)) {
            userEntity.getRoles().add(roleEntity); // Assuming AppUser has a Set<AppRole> roles
        }

        // Update the user in the database
        appUserDao.update(userEntity); // Update the user entity
    }

	/*@Override
	public Set<AppRoleDto> getUserRolesByUsername(String username) {
		return appUserDao.findUserByUsername(username)
	            .getRoles()
	            .stream()
	            .map(role -> new AppRoleDto(role.getId(), role.getNom())) // Map to RoleDto
	            .collect(Collectors.toSet());
	}*/
    @Override
    public Set<AppRoleDto> getUserRolesByUsername(String username) {
        // Récupérer l'utilisateur par son nom d'utilisateur
        //AppUser user = appUserDao.findUserByUsername(username);
    	AppUser user = (AppUser) appUserDao.getUserRolesByUsername(username);

        // Vérifier si l'utilisateur est `null`
        if (user == null) {
            throw new RuntimeException("User not found with username: " + username);
        }

        // Si l'utilisateur existe, retourner ses rôles mappés en DTO
        return user.getRoles()
                .stream()
                .map(role -> new AppRoleDto(role.getId(), role.getNom())) // Mapper en RoleDto
                .collect(Collectors.toSet());
    }

	@Override
	public Set<AppRoleDto> getUserRolesByUsernameNew(String email) {
		// Optional<AppUser> userOpt = appUserDao.findUserByEmail(email);
		Optional<AppUser> userOpt = Optional.ofNullable(appUserDao.findByEmail(email));
		    AppUser user = userOpt.orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found."));
		    
		    return user.getRoles().stream()
		               .map(role -> new AppRoleDto(role.getId(), role.getNom()))
		               .collect(Collectors.toSet());
	}

   
}

