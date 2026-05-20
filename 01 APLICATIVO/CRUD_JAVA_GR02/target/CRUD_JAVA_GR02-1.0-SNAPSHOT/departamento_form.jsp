<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="edu.ec.monster.modelo.Departamento"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= (request.getAttribute("departamento") != null) ? "Editar Departamento" : "Nuevo Departamento" %> - Monster</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body class="flex items-center justify-center min-h-screen">
    <%
        Departamento depto = (Departamento) request.getAttribute("departamento");
        boolean esEdicion = (depto != null);
    %>
    
    <div class="card" style="width: 100%; max-width: 500px; margin: var(--space-3);">
        <div class="card-header">
            <h2 class="text-xl" style="margin-bottom: 0;"><%= esEdicion ? "Editar Departamento" : "Nuevo Departamento" %></h2>
            <p class="text-sm text-secondary"><%= esEdicion ? "Actualizar los datos del departamento" : "Registrar un nuevo departamento en el sistema" %></p>
        </div>

        <div class="card-body">
            <form action="departamentos" method="POST" class="flex-col gap-3">
                <input type="hidden" name="accion" value="<%= esEdicion ? "actualizar" : "insertar" %>">
                
                <% if (esEdicion) { %>
                    <div class="form-group">
                        <label class="form-label">Código</label>
                        <input type="text" name="txtCodigo" class="input" value="<%= depto.getCodigo() %>" readonly style="background-color: var(--bg-base); color: var(--text-secondary);">
                        <span class="form-error" style="color: var(--text-secondary); margin-top: 4px; display: block;">El código no es editable.</span>
                    </div>
                    
                    <div class="form-group">
                        <label class="form-label">Fecha de Creación</label>
                        <input type="text" class="input" value="<%= depto.getFecha() %>" disabled style="background-color: var(--bg-base); color: var(--text-secondary);">
                    </div>
                <% } else { %>
                    <div class="alert alert-info">
                        <svg style="width: 20px; height: 20px; flex-shrink: 0;" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path></svg>
                        <span>El código y la fecha de creación se generarán automáticamente.</span>
                    </div>
                <% } %>
                
                <div class="form-group">
                    <label class="form-label" for="txtNombre">Nombre del Departamento</label>
                    <input type="text" id="txtNombre" name="txtNombre" class="input" required placeholder="Ej. Finanzas" value="<%= esEdicion ? depto.getNombre() : "" %>">
                </div>
                
                <div class="form-group">
                    <label class="form-label" for="txtDescripcion">Descripción</label>
                    <input type="text" id="txtDescripcion" name="txtDescripcion" class="input" required placeholder="Ej. Encargado de contabilidad" value="<%= esEdicion ? depto.getDescripcion() : "" %>">
                </div>
                
                <div class="flex gap-2" style="margin-top: var(--space-2);">
                    <button type="submit" class="btn btn-primary" style="flex: 1;">
                        <%= esEdicion ? "Guardar Cambios" : "Crear Departamento" %>
                    </button>
                    <a href="departamentos?accion=listar" class="btn btn-secondary">Cancelar</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>