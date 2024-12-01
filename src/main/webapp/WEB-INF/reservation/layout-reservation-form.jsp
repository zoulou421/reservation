<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservation Form</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <style>
        body {
             background-image: url('https://www.sanahotels.com/media/dzhp4ope/epic-sana-luanda-fitness.jpg'); /* Replace with your image link */
            color: white;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 15px;
            background-repeat:no-repeat;
            background-size:100%;
        }
        .form-container {
            background: rgba(255, 255, 255, 0.9);
            color: #333;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 500px;
        }
        .form-title {
            text-align: center;
            margin-bottom: 20px;
        }
        .btn-custom {
            background-color: #6a11cb;
            border-color: #6a11cb;
            color: white;
        }
        .btn-custom:hover {
            background-color: #2575fc;
            border-color: #2575fc;
        }
        .back-link {
            text-align: center;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2 class="form-title">${reservation != null ? 'Edit Reservation' : 'Add Reservation'}</h2>
        <!-- Form with dynamic action using contextPath -->
        <form action="${pageContext.request.contextPath}${reservation != null ? '/reservation?action=edit' : '/reservation?action=add'}" method="post">
            <input type="hidden" name="id" value="${reservation != null ? reservation.idDto : ''}">
            
            <div class="mb-3">
                <label for="customerName" class="form-label">Customer Name:</label>
                <input type="text" id="customerName" name="customerName" class="form-control" value="${reservation != null ? reservation.customerName : ''}" required>
            </div>
            
            <div class="mb-3">
                <label for="reservationDate" class="form-label">Reservation Date:</label>
                <input type="date" id="reservationDate" name="reservationDate" class="form-control" value="${reservation != null ? reservation.dateReservationDto != null ? reservation.dateReservationDto : '' : ''}" required>
            </div>

            <div class="mb-3">
                <label for="phoneNumber" class="form-label">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" class="form-control" value="${reservation != null ? reservation.phoneNumber : ''}">
            </div>
            
            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" id="email" name="email" class="form-control" value="${reservation != null ? reservation.email : ''}">
            </div>
            
            <div class="mb-3">
                <label for="numberOfGuests" class="form-label">Number of Guests:</label>
                <input type="number" id="numberOfGuests" name="numberOfGuests" class="form-control" value="${reservation != null ? reservation.numberOfGuestsDto : ''}" required>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-custom">${reservation != null ? 'Update Reservation' : 'Add Reservation'}</button>
            </div>
        </form>

        <div class="back-link">
            <a href="${pageContext.request.contextPath}/reservation?action=list" class="btn btn-outline-light">Back to Reservation List</a>
        </div>

        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3" role="alert">
                ${error}
            </div>
        </c:if>
    </div>

    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
