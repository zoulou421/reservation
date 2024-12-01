package com.ipd.reservation.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AppUserDto implements Serializable {

    private long id;
    private String userName;
    private String email;
    private String password; // Consider carefully about its necessity here
    private List<AppRoleDto> roles;  // List of AppRoleDto to represent user's roles

    // Default constructor
    public AppUserDto() {
        // No-argument constructor for serialization
    }

    // Parameterized constructor
    public AppUserDto(long id, String userName, String email, String password, List<AppRoleDto> roles) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password; // Ensure this is handled securely
        this.roles = roles;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password; // Handle securely, possibly exclude in certain contexts
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AppRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<AppRoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "AppUserDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AppUserDto)) return false;
        AppUserDto that = (AppUserDto) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, email, roles);
    }
}
