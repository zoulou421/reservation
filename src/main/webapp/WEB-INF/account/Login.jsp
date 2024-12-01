<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Connexion</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

  
  <!-- Login Form -->
  <div class="container py-5">
    <h2 class="text-center mb-4">Connexion</h2>
    <div class="row justify-content-center">
      <div class="col-md-6">
        <form  action="login" method="post">
          <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" name="email" class="form-control" id="email" placeholder="Entrez votre email"  required />
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">Mot de Passe</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="Entrez votre mot de passe"  required />
          </div>
          <button type="submit" class="btn btn-primary w-100">Se connecter</button>
        </form>
        <p><a href="#">Mot de passe oublié ? </a></p>
        <p class="text-center mt-3">Pas encore de compte ? <a href="${pageContext.request.contextPath}/register">S'inscrire</a></p>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
    