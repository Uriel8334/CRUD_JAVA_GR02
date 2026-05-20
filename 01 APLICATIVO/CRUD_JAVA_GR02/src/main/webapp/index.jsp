<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ingreso al Sistema - Monster</title>
    <!-- Incluimos nuestro nuevo Design System -->
    <link rel="stylesheet" href="estilos.css">
</head>
<body class="flex items-center justify-center min-h-screen">

    <div class="card" style="width: 100%; max-width: 400px; margin: var(--space-3);">
        <div class="card-header text-center">
            <h2 class="text-xl" style="margin-bottom: 0;">Monster</h2>
            <p class="text-sm text-secondary">Acceso de Seguridad - RRHH</p>
        </div>
        
        <div class="card-body">
            <% 
                // Esto captura el mensaje de error del Servlet si el usuario se equivoca de clave
                String mensajeError = (String) request.getAttribute("error");
                if (mensajeError != null) { 
            %>
                <div class="alert alert-error">
                    <span><%= mensajeError %></span>
                </div>
            <% } %>

            <form action="login" method="POST" class="flex-col gap-3">
                <div class="form-group">
                    <label class="form-label" for="txtCodigo">Código de Empleado</label>
                    <input type="text" id="txtCodigo" name="txtCodigo" class="input" placeholder="Ej. EMP-001" required>
                </div>
                
                <div class="form-group">
                    <label class="form-label" for="txtPassword">Contraseña</label>
                    <input type="password" id="txtPassword" name="txtPassword" class="input" placeholder="••••••••" required>
                </div>
                
                <button type="submit" class="btn btn-primary btn-block" style="margin-top: var(--space-2);">
                    Iniciar Sesión
                </button>
            </form>
        </div>
        
        <div class="card-footer text-center">
            <p class="text-xs text-secondary">¿Olvidaste tu contraseña? Contacta a soporte.</p>
        </div>
    </div>

</body>
</html>