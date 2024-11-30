package com.ipd.reservation.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", nullable = false, length = 200)
    private String userName;

    @Column(name = "email", nullable = false, length = 200, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 200)
    private String password;

 
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<AppRole> roles = new ArrayList<>();


	public AppUser() {
		super();
	}


	public AppUser(long id, String userName, String email, String password, List<AppRole> roles) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}


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
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<AppRole> getRoles() {
		return roles;
	}


	public void setRoles(List<AppRole> roles) {
		this.roles = roles;
	}
    
    
}
