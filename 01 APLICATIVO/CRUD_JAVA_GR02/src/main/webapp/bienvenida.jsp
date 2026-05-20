<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenido - Monster</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body class="flex items-center justify-center min-h-screen">

    <div class="card" style="width: 100%; max-width: 500px; margin: var(--space-3);">
        <!-- Header con un tono sutil de éxito -->
        <div class="card-header text-center" style="background-color: rgba(52, 199, 89, 0.05); border-bottom: 1px solid rgba(52, 199, 89, 0.1);">
            <h2 class="text-xl" style="color: #248A3D; margin-bottom: 0;">¡Autenticación Exitosa!</h2>
        </div>
        
        <div class="card-body text-center" style="padding: var(--space-5) var(--space-3);">
            <!-- Icono de éxito (Checkmark Apple-style) -->
            <svg style="width: 64px; height: 64px; color: var(--color-success); margin: 0 auto var(--space-3);" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"></path>
            </svg>
            
            <h3 class="text-2xl" style="margin-bottom: var(--space-1);">Bienvenido al Sistema</h3>
            <p class="text-base text-secondary">Has ingresado correctamente a la plataforma de gestión de proyectos Monster.</p>
        </div>
        
        <div class="card-footer flex justify-center gap-2">
            <!-- Botón primario para continuar y uno fantasma para salir -->
            <button class="btn btn-primary" onclick="window.location.href='departamentos?accion=listar'" class="tu-clase-css">
                Ir al Panel de Control
            </button>
            <button class="btn btn-ghost" onclick="window.location.href='index.jsp'">Cerrar Sesión</button>
        </div>
    </div>

</body>
</html>