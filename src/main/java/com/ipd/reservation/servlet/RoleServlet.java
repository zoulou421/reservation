package com.ipd.reservation.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipd.reservation.dto.AppRoleDto;
import com.ipd.reservation.service.AppRoleService;
import com.ipd.reservation.service.IAppRoleService;

@WebServlet("/role")
public class RoleServlet extends HttpServlet {

    private IAppRoleService roleService;

    @Override
    public void init() throws ServletException {
        try {
            // Initialize EntityManager
            //EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();

            // Initialize DAO and service with EntityManager
            //AppRoleDao appRoleDao = new AppRoleDao(entityManager);
            //roleService = new AppRoleService(appRoleDao); // Use DAO
            roleService = new AppRoleService();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize RoleServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            listRoles(request, response);
            return;
        }

        switch (action) {
            case "add":
                showRoleForm(request, response, null);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteRole(request, response);
                break;
            default:
                listRoles(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String name = request.getParameter("nom"); // Assuming "nom" is the property name for role name

        AppRoleDto roleDto = new AppRoleDto();
        roleDto.setId(idStr != null && !idStr.isEmpty() ? Long.parseLong(idStr) : 0);
        roleDto.setNom(name);

        if (roleDto.getId() > 0) {
            roleService.updateRole(roleDto);
        } else {
            roleService.createRole(roleDto);
        }

        response.sendRedirect(request.getContextPath() + "/role?action=list");
    }

    private void listRoles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<AppRoleDto> roleList = roleService.listRoles();
        request.setAttribute("roleList", roleList);
        request.getRequestDispatcher("/WEB-INF/views/layout-role-list.jsp").forward(request, response);
    }

    private void showRoleForm(HttpServletRequest request, HttpServletResponse response, AppRoleDto roleDto) throws ServletException, IOException {
        request.setAttribute("role", roleDto);
        request.getRequestDispatcher("/WEB-INF/views/layout-role-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        AppRoleDto role = (AppRoleDto) roleService.getRoleById(id);
        if (role != null) {
            showRoleForm(request, response, role);
        } else {
            response.sendRedirect(request.getContextPath() + "/role?action=list");
        }
    }

    private void deleteRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        roleService.deleteRole(id);
        response.sendRedirect(request.getContextPath() + "/role?action=list");
    }
}
