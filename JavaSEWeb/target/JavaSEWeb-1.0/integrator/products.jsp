<%@page import="java.util.ArrayList"%>
<%@page import="com.eduit.javaseweb.integrator.models.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--meta http-equiv="Content-Type" content="text/html; charset=UTF-8"-->
        <title>List of Products</title>
        <link rel="stylesheet" href="../css/styles.css">
    </head>
    <body>
        <% String msg = String.valueOf(request.getSession().getAttribute("msg"));
            String error = String.valueOf(request.getSession().getAttribute("error"));
            List<Product> products = (List<Product>) request.getSession().getAttribute("products");
            if (products == null) {
                products = new ArrayList<Product>();
            }
        %>
        <header>
            <%@include file="../topBanner.jsp" %>
        </header>
        <div>
            <table style="width: 100%">
                <th style="text-align: left">
                    <h1>Products</h1>
                </th>
                <th style="text-align: right; padding-right: 10px">
                    Back<a href="../index.jsp"><img src="../resources/images/volver.png" width="25" height="25" alt="Volver"/></a>
                </th>
            </table>
        </div>
        <fieldset class="wrapper">
            <legend>List of Products</legend>
            <%if (error != null && !error.equals("null") && !error.isEmpty()) {
                    out.print("<div class=\"error\"><p>" + error + "</p></div>");
                } else if (msg != null && !msg.equals("null") && !msg.isEmpty()) {
                    out.print("<div class=\"sucess\"><p>" + msg + "</p></div>");
                }%>
            <fieldset>
                <legend>Search by</legend>
                <form method="get" action="../products">
                    <table>
                        <td class="firstColumn">Id: </td>
                        <td><input type="number" name="id"/></td>
                        <td class="firstColumn">Description: </td>
                        <td><input type="text" name="description"/></td>
                        <td><input type="submit" name="action" value="Filter" src="../imagenes/iconos/busqueda.png" width="20" height="20"/></td>
                    </table>
                </form>
            </fieldset>
            <br/>
            <a href="../products?action=New"><img src="../resources/images/bloc.png" width="40" height="40" style="margin-bottom: 5px"/></a>
            <br/>
            <table border="1" style="width: 100%" class="tblProducts">
                <tr>
                    <th style="width: 5%">ID</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th style="width: 10%">Action</th>
                </tr>
                <%for (Product product : products) {%>
                <tr>
                    <td style="text-align: center"><%=product.getId()%></td>
                    <td><%=product.getDescription()%></td>
                    <td style="text-align: center">$<%=product.getPrice()%></td>
                    <td style="text-align: center">
                        <a href="../products?action=Delete&id=<%=product.getId()%>"><img src="../resources/images/basura.png" alt="Borrar" width="30" height="30"/></a>
                        <a href="../products?action=Get&id=<%=product.getId()%>"><img src="../resources/images/lapiz.png" alt="Borrar" width="30" height="30"/></a>
                    </td>
                </tr>
                <%}%>
            </table>
        </fieldset>
    </body>
</html>
