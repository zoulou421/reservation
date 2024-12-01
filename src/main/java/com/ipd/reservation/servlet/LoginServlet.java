package com.ipd.reservation.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipd.reservation.dto.AppUserDto;
import com.ipd.reservation.service.AppUserService;
import com.ipd.reservation.service.IAppUserService;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    private IAppUserService userService;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        try {
           // EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
          //  AppUserDao appUserDao = new AppUserDao(entityManager);
          //  AppRoleDao appRoleDao = new AppRoleDao(entityManager);
            //userService = new AppUserService(appUserDao, appRoleDao); // Initialize userService
            userService = new AppUserService(); 
            System.out.println("UserService initialized successfully");
        } catch (Exception e) {
            throw new ServletException("Failed to initialize UserServlet", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Forward to login page
        req.getRequestDispatcher("/WEB-INF/account/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        // Log the email for debugging
        log.info("Email sent is {}", email);
        try {
            // Perform login with the password
            AppUserDto user = userService.login(email, password);
            if (user != null) {
                req.getSession().setAttribute("email", email);
                log.info("Login successful, redirecting to user page.");
               // resp.sendRedirect("user"); // Redirect to the user page
                resp.sendRedirect(req.getContextPath() + "/user");  // Ensure full context path is used

            } else {
                log.warn("Login failed for user: {}", email);
                resp.sendRedirect("login?error=true"); // Redirect back to login page with error
            }
        } catch (RuntimeException e) {
            log.error("Login failed: {}", e.getMessage());
            resp.sendRedirect("login?error=true"); // Redirect back to login page with error
        }

        
    }

    
}
