package com.ipd.reservation.dto;

import java.io.Serializable;

public class AppRoleDto implements Serializable {
    private long id;
    private String nom; // Changed 'name' to 'nom' for clarity

    // Default constructor
    public AppRoleDto() {
        // No need to call super() explicitly
    }

    // Parameterized constructor
    public AppRoleDto(long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() { // Updated to match the 'name' variable
        return nom;
    }

    public void setNom(String nom) { // Updated to match the 'name' variable
        this.nom = nom;
    }
    
    @Override
    public String toString() {
        return "AppRoleDto{id=" + id + ", nom='" + nom + "'}";
    }
}
