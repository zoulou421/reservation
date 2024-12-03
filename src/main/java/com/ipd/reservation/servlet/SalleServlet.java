package com.ipd.reservation.servlet;

import com.ipd.reservation.dto.SalleDto;
import com.ipd.reservation.service.SalleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SalleServlet", urlPatterns = {"/salle"})
public class SalleServlet extends HttpServlet {
    private SalleService salleService;

    @Override
    public void init() throws ServletException {
        salleService = new SalleService(); // Service layer instance
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                showForm(request, response, null);
                break;
            case "edit":
                long id = Long.parseLong(request.getParameter("id"));
                SalleDto salle = salleService.getSalleById(id);
                showForm(request, response, salle);
                break;
            case "delete":
                long salleId = Long.parseLong(request.getParameter("id"));
                salleService.deleteSalle(salleId);
                response.sendRedirect("salle");
                break;
            default:
                listSalles(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String nom = request.getParameter("nom");
        int capacite = Integer.parseInt(request.getParameter("capacite"));

        SalleDto salleDto = new SalleDto();
        salleDto.setNomDto(nom);
        salleDto.setCapaciteDto(capacite);

        if (idParam != null && !idParam.isEmpty()) {
            salleDto.setIdDto(Long.parseLong(idParam));
            salleService.updateSalle(salleDto);
        } else {
            salleService.saveSalle(salleDto);
        }

        response.sendRedirect("salle");
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response, SalleDto salle)
            throws ServletException, IOException {
        request.setAttribute("salle", salle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/salle/salleForm.jsp");
        dispatcher.forward(request, response);
    }

    private void listSalles(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<SalleDto> salles = salleService.listSalle();
        request.setAttribute("salles", salles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/salle/salleList.jsp");
        dispatcher.forward(request, response);
    }
}
