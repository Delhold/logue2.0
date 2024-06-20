<%@ page import="com.elvis.login.logueo.models.Producto" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de productos</title>
    <!-- Agrega tus estilos CSS si los tienes -->
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<header>
    <h1>Listado de productos</h1>
    <nav>
        <ul>
            <li><a href="${request.getContextPath()}/textileria_war/jsp/administradora.jsp">Inicio</a></li>
        </ul>
    </nav>
</header>

<main>
    <section>
        <h2>Listado de productos</h2>
        <%
            String username = (String) request.getAttribute("username");
            if (username != null) {
        %>
        <div style="color:blue;">HOLA <%= username %>, ¡Bienvenido!</div>
        <%
            }
        %>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Categoría</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Agregar</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Producto> productos = (List<Producto>) request.getAttribute("productos");
                if (productos != null && !productos.isEmpty()) {
                    for (Producto producto : productos) {
            %>
            <tr>
                <td><%= producto.getIdProducto() %></td>
                <td><%= producto.getNombre() %></td>
                <td><%= producto.getCategoria().getNombre() %></td>
                <td><%= producto.getDescripcion() %></td>
                <td><%= producto.getPrecio() %></td>
                <td><a href="<%= request.getContextPath() %>/agregar-carro?id=<%= producto.getIdProducto() %>">Agregar al carro</a></td>
            </tr>
            <%
                    }
                } else {
                    out.println("<tr><td colspan='6'>No hay productos disponibles</td></tr>");
                }
            %>
            </tbody>
        </table>
    </section>
</main>

<footer>
    <p>&copy; 2024 Textileria App. Todos los derechos reservados.</p>
</footer>
</body>
</html>
