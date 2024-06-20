<%--
  Created by IntelliJ IDEA.
  User: ADMIN-ITQ
  Date: 13/6/2024
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.elvis.login.logueo.models.*" %>
<%Carro carro = (Carro) session.getAttribute("carro");%>
<html>
<head>
    <title>Carro de compras</title>
</head>
<body>
    <h1>Carro de compras</h1>
    <%if (carro == null || carro.getItems().isEmpty()){%>
    <p>Lo sentimos no hay productos en el carro de compras!</p>
    <%}else{%>
    <table>
        <tr>
            <th>ID PRODUCTO</th>
            <th>NOMBRE</th>
            <th>PRECIO</th>
            <th>CANTIDAD</th>
            <th>SUBTOTAL</th>
        </tr>
        <%for(ItemCarro item : carro.getItems()){%>
        <tr>
            <td><%=item.getProducto().getIdProducto()%></td>
            <td><%= item.getProducto().getNombre()%></td>
            <td><%= item.getProducto().getPrecio()%></td>
            <td><%=item.getCantidad()%></td>
            <td><%=item.getImporte()%></td>
        </tr>
        <%}%>
        <tr>
            <td colspan="4">Total :</td>
            <td><%=carro.getTotal()%></td>
        </tr>
    </table>
    <%}%>
    <p><a href="<%=request.getContextPath()%>/productos">seguir comprando</a></p>
    <p><a href="<%=request.getContextPath()%>/index.html">Volver</a></p>


</body>
</html>
