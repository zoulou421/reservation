package com.ipd.reservation.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="seance")
public class Seance implements Serializable{
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Temporal(TemporalType.DATE)
	    private Date dateSeance;

	    private int duree; // en minutes
	    private String type;
	    
	    @OneToMany(mappedBy = "seance", cascade = CascadeType.ALL)
	    private List<Reservation> reservations;
	    
	    @ManyToOne
	    @JoinColumn(name = "salle_id")
	    private Salle salle;

		public Seance() {
			super();
		}

		public Seance(Long id, Date dateSeance, int duree, String type, List<Reservation> reservations, Salle salle) {
			super();
			this.id = id;
			this.dateSeance = dateSeance;
			this.duree = duree;
			this.type = type;
			this.reservations = reservations;
			this.salle = salle;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getDateSeance() {
			return dateSeance;
		}

		public void setDateSeance(Date dateSeance) {
			this.dateSeance = dateSeance;
		}

		public int getDuree() {
			return duree;
		}

		public void setDuree(int duree) {
			this.duree = duree;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<Reservation> getReservations() {
			return reservations;
		}

		public void setReservations(List<Reservation> reservations) {
			this.reservations = reservations;
		}

		public Salle getSalle() {
			return salle;
		}

		public void setSalle(Salle salle) {
			this.salle = salle;
		}
	    
	    

}
