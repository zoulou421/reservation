<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Salle Management</title>
    <!--<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

</head>
<body>
    <div class="container mt-5">
        <h1>Salles List</h1>
        
        <!-- Add New Reservation Button -->
        <div class="back-link">
            <a href="${pageContext.request.contextPath}/salle?action=add" class="btn btn-primary mb-3">
                <i class="bi bi-plus-circle"></i> Ajouter une nouvelle Salle
            </a>
        </div>
        
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Capacity</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="salle" items="${salles}">
                    <tr>
                        <td>${salle.idDto}</td>
                        <td>${salle.nomDto}</td>
                        <td>${salle.capaciteDto}</td>
                        <td>
                            <a href="salle?action=edit&id=${salle.idDto}" class="btn btn-warning">Edit</a>
                            <a href="salle?action=delete&id=${salle.idDto}" class="btn btn-danger" onclick="return confirm('Are you sure?')">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
        
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
