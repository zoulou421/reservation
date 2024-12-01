package com.ipd.reservation.filters;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipd.reservation.dto.AppRoleDto;
import com.ipd.reservation.service.AppUserService;
import com.ipd.reservation.service.IAppUserService;

@WebFilter({"/admin/*", "/user/*", "/role/*"})
public class AuthFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);
    private IAppUserService userService=null;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        try {
            // Initialize the necessary DAOs and services
           /* EntityManager entityManager = HibernateUtil.getSessionFactory().createEntityManager();
            AppUserDao appUserDao = new AppUserDao(entityManager);
            AppRoleDao appRoleDao = new AppRoleDao(entityManager);*/

            //userService = new AppUserService(appUserDao, appRoleDao);
            userService = new AppUserService();

            logger.info("UserService and RoleService initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize AuthFilter", e);
            throw new ServletException("Failed to initialize AuthFilter", e);
        }
    }

   /* @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // Avoid creating a new session

        // Retrieve the email from the session, if it exists
        String email = (session != null) ? (String) session.getAttribute("email") : null;


        // Get the requested path
        String path = req.getServletPath();

        // Allow access to login and logout paths without authentication
        if (path.equals("/login") || path.equals("/logout")) {
            logger.debug("Allowing access to: {}", path);
            chain.doFilter(request, response); // Allow the request
        } else if (email != null) {
            // Fetch user roles from the database using userService
            logger.info("Attempting to fetch roles for email: {}", email);
            Set<AppRoleDto> userRoles = userService.getUserRolesByUsernameNew(email); // Fetch roles

            // Log the roles (streamed into a list)
            if (userRoles != null && !userRoles.isEmpty()) {
                List<String> roleNames = userRoles.stream()
                        .map(AppRoleDto::getNom) // Extract role name
                        .collect(Collectors.toList()); // Collect into a List

                logger.debug("User roles: {}", roleNames); // Log the list of role names

                boolean hasAccess = false;

                // Role-based access control
                if (roleNames.contains("ROLE_ADMIN") && path.startsWith("/user/")) {
                    hasAccess = true; // Allow admin access
                } else if (roleNames.contains("ROLE_USER") && (path.startsWith("/user") ||path.startsWith("/role/"))) {
                    hasAccess = true; // Allow user access
                    logger.info("Check hasAccess boolean ROLE_USER: {}", hasAccess);
                    
                } else if (roleNames.contains("ROLE_USER") && path.startsWith("/role/")) {
                    hasAccess = true; // Allow role access (if needed)
                } else if (roleNames.contains("ROLE_ADMIN") && path.startsWith("/role/")) {
                    hasAccess = true; // Allow role access (if needed)
                }

                if (hasAccess) {
                    logger.debug("Access granted for path: {}", path);
                    chain.doFilter(request, response); // Allow the request if access is granted
                } else {
                    logger.warn("Access denied for path: {}. User does not have appropriate role.", path);
                    resp.sendRedirect(req.getContextPath() + "/accessDenied"); // Redirect to access denied page
                }
            } else {
                logger.warn("No roles found for user: {}", email);
                resp.sendRedirect(req.getContextPath() + "/accessDenied"); // Redirect if no roles are assigned
            }
        } else {
            logger.info("User is not authenticated, redirecting to login.");
            resp.sendRedirect(req.getContextPath() + "/login"); // Redirect to login page if not authenticated
        }
    }*/
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        String email = (session != null) ? (String) session.getAttribute("email") : null;
        String path = req.getServletPath();

        // Allow access to login and logout paths without authentication
        if ("/login".equals(path) || "/logout".equals(path)) {
            chain.doFilter(request, response);
            return;
        }

        if (email != null) {
            // Fetch user roles from the service
            Set<AppRoleDto> userRoles = userService.getUserRolesByUsernameNew(email);
            List<String> roleNames = userRoles.stream().map(AppRoleDto::getNom).collect(Collectors.toList());

            // Define access rules
            boolean hasUserAccess = roleNames.contains("ROLE_USER") && (path.startsWith("/home") 
            		|| path.startsWith("/about") || path.startsWith("/clientAuthWSocket"));
            boolean hasManagerAccess = roleNames.contains("ROLE_COMPTABLE") && (path.startsWith("/user") || path.startsWith("/role"));
            boolean hasAdminAccess = roleNames.contains("ROLE_ADMIN") && (path.startsWith("/admin") || path.startsWith("/user") 
            		|| path.startsWith("/role") || path.startsWith("/serverAuthWSocket"));
            boolean hasDevAccess = roleNames.contains("ROLE_DEV") && (path.startsWith("/user") || path.startsWith("/role"));
            // Allow the request if the user has the required access
            if (hasUserAccess || hasAdminAccess || hasDevAccess || hasManagerAccess) {
                chain.doFilter(request, response); // Proceed with the request
            } else {
                // Redirect to access denied page if access is not allowed
                resp.sendRedirect(req.getContextPath() + "/accessDenied");
            }
        } else {
            // Redirect to login page if user is not authenticated
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }



    @Override
    public void destroy() {
        // Cleanup code, if needed
        logger.info("AuthFilter destroyed.");
    }
}
