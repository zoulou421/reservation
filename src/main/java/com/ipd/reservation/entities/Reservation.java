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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="reservation")
public class Reservation implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date dateReservation;

    private String status;
   
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Notifications> notifications;
    
    @ManyToOne
    @JoinColumn(name = "seance_id")
    private Seance seance;
    
    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Paiement paiement;

    private String customerName;  // Added customerName field
    private String phoneNumber;   // Added phoneNumber field
    private String email;         // Added email field
    private int numberOfGuests;   // Added numberOfGuests field

    // Default constructor
    public Reservation() {
        super();
    }

    // Constructor with all fields
    public Reservation(long id, Date dateReservation, String status, List<Notifications> notifications,
                       Seance seance, Paiement paiement, String customerName, String phoneNumber, 
                       String email, int numberOfGuests) {
        super();
        this.id = id;
        this.dateReservation = dateReservation;
        this.status = status;
        this.notifications = notifications;
        this.seance = seance;
        this.paiement = paiement;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.numberOfGuests = numberOfGuests;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(Date dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Notifications> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notifications> notifications) {
        this.notifications = notifications;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }
}
