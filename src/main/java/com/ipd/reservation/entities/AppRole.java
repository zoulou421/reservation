package com.ipd.reservation.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class AppRole implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom", nullable = false, length = 200, unique = true)
    private String nom;

    @ManyToMany(mappedBy = "roles")
    private List<AppUser> users = new ArrayList<>();

	public AppRole() {
		super();
	}

	public AppRole(long id, String nom, List<AppUser> users) {
		super();
		this.id = id;
		this.nom = nom;
		this.users = users;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<AppUser> getUsers() {
		return users;
	}

	public void setUsers(List<AppUser> users) {
		this.users = users;
	}
    
}
