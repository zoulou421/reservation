<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Bienvenue</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Image de fond */
    body {
      background-image: url('https://www.sanahotels.com/media/dzhp4ope/epic-sana-luanda-fitness.jpg'); /* Remplacez par le lien de votre image */
      background-size: cover;
      background-position: center;
      background-repeat: no-repeat;
    }
    
    /* Overlay pour obscurcir légèrement l'image de fond */
    .overlay {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.6); /* Ajuster l'opacité pour un effet de sombre */
      z-index: 1;
    }
    
    /* Conteneur de bienvenue */
    .welcome-container {
      position: relative;
      z-index: 2; /* Position au-dessus de l'overlay */
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      color: white;
    }
    
    /* Style du message de bienvenue */
    .welcome-message {
      font-size: 3rem;
      font-weight: bold;
      color: #ffffff;
    }
  </style>
</head>
<body>

  <!-- Overlay pour obscurcir l'image de fond -->
  <div class="overlay"></div>
  
  <!-- Conteneur de bienvenue -->
  <div class="welcome-container text-center">
    <div>
      <p class="welcome-message">Bienvenue dans l'application backend</p>
      <p class="lead">Nous sommes ravis de vous retrouver !</p>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
