package com.ipd.reservation.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipd.reservation.dto.ReservationDto;
import com.ipd.reservation.service.IReservationService;
import com.ipd.reservation.service.ReservationService;

@WebServlet(name="reservation", urlPatterns="/reservation")
public class ReservationServlet extends HttpServlet {

    private IReservationService reservationService;

    @Override
    public void init() throws ServletException {
        try {
            reservationService = new ReservationService(); // Initialize the reservation service
            System.out.println("ReservationService initialized successfully");
        } catch (Exception e) {
            throw new ServletException("Failed to initialize ReservationService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            listReservations(request, response);
        } else {
            switch (action) {
                case "add":
                    showReservationForm(request, response, null);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteReservation(request, response);
                    break;
                default:
                    listReservations(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve parameters from the request
            String idStr = request.getParameter("id");
            String customerName = request.getParameter("customerName");
            String reservationDateStr = request.getParameter("reservationDate");
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String numberOfGuestsStr = request.getParameter("numberOfGuests");

            // Validate required fields
            if (customerName == null || customerName.isEmpty()) {
                throw new IllegalArgumentException("Customer name is required.");
            }

            // Populate DTO
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setIdDto(idStr != null && !idStr.isEmpty() ? Long.parseLong(idStr) : 0);
            reservationDto.setCustomerNameDto(customerName);

            // Date parsing with exception handling
            if (reservationDateStr != null && !reservationDateStr.isEmpty()) {
                try {
                    Date reservationDate = new SimpleDateFormat("yyyy-MM-dd").parse(reservationDateStr);
                    reservationDto.setDateReservationDto(reservationDate);
                } catch (Exception e) {
                    throw new IllegalArgumentException("Invalid reservation date format. Use yyyy-MM-dd.");
                }
            }

            reservationDto.setPhoneNumberDto(phoneNumber);
            reservationDto.setEmailDto(email);
            reservationDto.setNumberOfGuestsDto(numberOfGuestsStr != null && !numberOfGuestsStr.isEmpty() ?
                    Integer.parseInt(numberOfGuestsStr) : 0);

            // Save or update based on ID
            if (reservationDto.getIdDto() > 0) {
                reservationService.updateReservation(reservationDto);
            } else {
                reservationService.addReservation(reservationDto);
            }

            response.sendRedirect(request.getContextPath() + "/reservation?action=list");
        } catch (Exception e) {
            request.setAttribute("error", "Error while processing the reservation form: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/reservation/layout-reservation-form.jsp").forward(request, response);
        }
    }

    private void listReservations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ReservationDto> reservationList = reservationService.getAllReservations();
        request.setAttribute("reservationList", reservationList);
        request.getRequestDispatcher("/WEB-INF/reservation/layout-reservation-list.jsp").forward(request, response);
    }

    private void showReservationForm(HttpServletRequest request, HttpServletResponse response, ReservationDto reservationDto) throws ServletException, IOException {
        request.setAttribute("reservation", reservationDto);
        request.getRequestDispatcher("/WEB-INF/reservation/layout-reservation-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            ReservationDto reservation = reservationService.getReservationById(id);
            if (reservation != null) {
                showReservationForm(request, response, reservation);
            } else {
                response.sendRedirect(request.getContextPath() + "/reservation?action=list");
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/reservation?action=list");
        }
    }

    private void deleteReservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            reservationService.deleteReservationById(id);
        } catch (NumberFormatException e) {
            System.out.println("Invalid reservation ID for deletion: " + e.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/reservation?action=list");
    }
}
