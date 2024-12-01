package com.ipd.reservation.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ReservationDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private long idDto;
    private Date dateReservationDto;
    private String statusDto;
    private List<NotificationsDto> notificationsDto;
    private SeanceDto seanceDto;
    private PaiementDto paiementDto;
    private String customerNameDto;
    private String phoneNumberDto;  // Added phoneNumber field
    private String emailDto;        // Added email field
    private int numberOfGuestsDto;  // Added numberOfGuests field

    // Default constructor
    public ReservationDto() {
        super();
    }

    // Constructor with all fields
    public ReservationDto(long idDto, Date dateReservationDto, String statusDto,
                          List<NotificationsDto> notificationsDto, SeanceDto seanceDto,
                          PaiementDto paiementDto, String customerNameDto, String phoneNumberDto,
                          String emailDto, int numberOfGuestsDto) {
        super();
        this.idDto = idDto;
        this.dateReservationDto = dateReservationDto;
        this.statusDto = statusDto;
        this.notificationsDto = notificationsDto;
        this.seanceDto = seanceDto;
        this.paiementDto = paiementDto;
        this.customerNameDto = customerNameDto;
        this.phoneNumberDto = phoneNumberDto;
        this.emailDto = emailDto;
        this.numberOfGuestsDto = numberOfGuestsDto;
    }

    // Getters and Setters
    public long getIdDto() {
        return idDto;
    }

    public void setIdDto(Long idDto) {
        this.idDto = idDto;
    }

    public Date getDateReservationDto() {
        return dateReservationDto;
    }

    public void setDateReservationDto(Date dateReservationDto) {
        this.dateReservationDto = dateReservationDto;
    }

    public String getStatusDto() {
        return statusDto;
    }

    public void setStatusDto(String statusDto) {
        this.statusDto = statusDto;
    }

    public List<NotificationsDto> getNotificationsDto() {
        return notificationsDto;
    }

    public void setNotificationsDto(List<NotificationsDto> notificationsDto) {
        this.notificationsDto = notificationsDto;
    }

    public SeanceDto getSeanceDto() {
        return seanceDto;
    }

    public void setSeanceDto(SeanceDto seanceDto) {
        this.seanceDto = seanceDto;
    }

    public PaiementDto getPaiementDto() {
        return paiementDto;
    }

    public void setPaiementDto(PaiementDto paiementDto) {
        this.paiementDto = paiementDto;
    }

    public String getCustomerNameDto() {
        return customerNameDto;
    }

    public void setCustomerNameDto(String customerNameDto) {
        this.customerNameDto = customerNameDto;
    }

    public String getPhoneNumberDto() {
        return phoneNumberDto;
    }

    public void setPhoneNumberDto(String phoneNumberDto) {
        this.phoneNumberDto = phoneNumberDto;
    }

    public String getEmailDto() {
        return emailDto;
    }

    public void setEmailDto(String emailDto) {
        this.emailDto = emailDto;
    }

    public int getNumberOfGuestsDto() {
        return numberOfGuestsDto;
    }

    public void setNumberOfGuestsDto(int numberOfGuestsDto) {
        this.numberOfGuestsDto = numberOfGuestsDto;
    }
}
