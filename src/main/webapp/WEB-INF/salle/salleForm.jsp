<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${salle != null ? 'Edit' : 'Add'} Salle</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1>${salle != null ? 'Edit' : 'Add'} Salle</h1>
        <form action="salle" method="POST">
            <div class="mb-3">
                <label for="nom" class="form-label">Salle Name</label>
                <input type="text" class="form-control" id="nom" name="nom" 
                       value="${salle != null ? salle.nomDto : ''}" required>
            </div>
            <div class="mb-3">
                <label for="capacite" class="form-label">Capacity</label>
                <input type="number" class="form-control" id="capacite" name="capacite" 
                       value="${salle != null ? salle.capaciteDto : ''}" required>
            </div>
            <input type="hidden" name="id" value="${salle != null ? salle.idDto : ''}">
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
        <a href="salle" class="btn btn-secondary mt-3">Back to List</a>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
