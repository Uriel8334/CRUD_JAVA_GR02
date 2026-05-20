<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="edu.ec.monster.modelo.Departamento"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Departamentos - Monster</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body class="flex-col min-h-screen">
    
    <div class="container" style="padding-top: var(--space-5); padding-bottom: var(--space-5);">
        
        <div class="flex justify-between items-center" style="margin-bottom: var(--space-4);">
            <div>
                <h2 class="text-2xl" style="margin-bottom: var(--space-1);">Departamentos</h2>
                <p class="text-sm text-secondary">Gestión de departamentos de Recursos Humanos</p>
            </div>
            <a href="departamentos?accion=nuevo" class="btn btn-primary">
                <svg style="width: 20px; height: 20px; margin-right: 4px;" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4"></path></svg>
                Nuevo Departamento
            </a>
        </div>

        <div class="card">
            <div class="card-body" style="padding: 0;">
                <% 
                    List<Departamento> lista = (List<Departamento>) request.getAttribute("listaDepartamentos");
                    if (lista != null && !lista.isEmpty()) { 
                %>
                <div class="table-container" style="border: none; border-radius: 0;">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Código</th>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th>Fecha Creación</th>
                                <th style="text-align: right;">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Departamento d : lista) { %>
                                <tr>
                                    <td style="font-weight: 500;"><%= d.getCodigo() %></td>
                                    <td><%= d.getNombre() %></td>
                                    <td class="text-secondary"><%= d.getDescripcion() %></td>
                                    <td class="text-secondary"><%= d.getFecha() %></td>
                                    <td style="text-align: right;">
                                        <a href="departamentos?accion=editar&codigo=<%= d.getCodigo() %>" class="btn btn-ghost" style="padding: var(--space-1); color: var(--color-primary);">Editar</a>
                                        <a href="departamentos?accion=eliminar&codigo=<%= d.getCodigo() %>" class="btn btn-ghost" style="padding: var(--space-1); color: var(--color-error);" onclick="return confirm('¿Estás seguro de eliminar el departamento <%= d.getNombre() %>?');">Eliminar</a>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
                <% } else { %>
                <div style="padding: var(--space-5); text-align: center;">
                    <svg style="width: 48px; height: 48px; color: var(--color-gray-400); margin: 0 auto var(--space-2);" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4"></path></svg>
                    <p class="text-base text-secondary">No hay departamentos registrados.</p>
                </div>
                <% } %>
            </div>
        </div>

        <div style="margin-top: var(--space-4);">
            <a href="bienvenida.jsp" class="btn btn-secondary">Volver al Inicio</a>
        </div>
    </div>
</body>
</html>