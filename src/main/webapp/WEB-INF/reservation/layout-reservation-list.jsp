<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation List</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">

    <style>
        /* Global Styles */
        body {
            background: linear-gradient(to bottom, #6a11cb, #2575fc);
            color: white;
            min-height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            padding: 15px;
        }

        .container {
            background: rgba(255, 255, 255, 0.9);
            color: #333;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 1200px;
        }

        /* Heading and Table Container */
        h2 {
            text-align: center;
            margin-bottom: 30px;
        }

        .table-container {
            margin-top: 20px;
        }

        /* Button Customization */
        .btn-custom {
            background-color: #6a11cb;
            border-color: #6a11cb;
            color: white;
        }

        .btn-custom:hover {
            background-color: #2575fc;
            border-color: #2575fc;
        }

        /* Action Links */
        .action-links a {
            color: #6a11cb;
            text-decoration: none;
            margin-right: 10px;
        }

        .action-links a:hover {
            color: #2575fc;
        }

        /* No Reservation Found */
        .no-reservation {
            text-align: center;
            color: #f8d7da;
            font-weight: bold;
        }

        /* Table Styles */
        .table th,
        .table td {
            text-align: center;
            vertical-align: middle;
            padding: 12px;
        }

        .table th {
            background-color: #6a11cb;
            color: white;
        }

        .table tr:hover {
            background-color: #f1f1f1;
        }

        /* Table Action Buttons */
        .table td a {
            display: inline-flex;
            align-items: center;
        }

        .table td a i {
            margin-right: 5px;
        }

        /* Styling for Adding More Space Between Rows */
        .table-bordered {
            border: 2px solid #ddd;
        }

        .table-bordered th,
        .table-bordered td {
            border: 1px solid #ddd;
        }

        /* Responsive Table */
        .table-container {
            margin-top: 30px;
        }

        /* Back Button */
        .back-link {
            text-align: center;
            margin-top: 20px;
        }
    </style>

</head>

<body>
    <div class="container">

        <!-- Heading -->
        <h2>Liste des Réservations</h2>

        <!-- Reservation List Table -->
        <c:if test="${not empty reservationList}">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom du client</th>
                        <th>Date de réservation</th>
                        <th>Numéro de téléphone</th>
                        <th>Email</th>
                        <th>Nombre de personnes</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="reservation" items="${reservationList}">
                        <tr>
                            <td>${reservation.idDto}</td>
                            <td>${reservation.customerNameDto}</td>
                            <td>${reservation.dateReservationDto != null ? reservation.dateReservationDto : 'N/A'}</td>
                            <td>${reservation.phoneNumberDto != null ? reservation.phoneNumberDto : 'N/A'}</td>
                            <td>${reservation.emailDto != null ? reservation.emailDto : 'N/A'}</td>
                            <td>${reservation.numberOfGuestsDto != null ? reservation.numberOfGuestsDto : 'N/A'}</td>
                            <td class="action-links">
                                <a href="${pageContext.request.contextPath}/reservation?action=edit&id=${reservation.idDto}"
                                    class="btn btn-sm btn-outline-primary">
                                    <i class="bi bi-pencil"></i> Modifier
                                </a>
                                <a href="${pageContext.request.contextPath}/reservation?action=delete&id=${reservation.idDto}"
                                    onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette réservation ?')"
                                    class="btn btn-sm btn-outline-danger">
                                    <i class="bi bi-trash"></i> Supprimer
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <!-- No Reservations Found -->
        <c:if test="${empty reservationList}">
            <p class="no-reservation">Aucune réservation trouvée.</p>
        </c:if>

        <!-- Add New Reservation Button -->
        <div class="back-link">
            <a href="${pageContext.request.contextPath}/reservation?action=add" class="btn btn-custom">
                <i class="bi bi-plus-circle"></i> Ajouter une nouvelle réservation
            </a>
        </div>

    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
