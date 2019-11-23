<%@page import="java.util.ArrayList"%>
<%@page import="com.eduit.javaseweb.integrator.models.Product"%>
<%@page import="com.eduit.javaseweb.integrator.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Products</title>
        <link rel="stylesheet" href="../css/styles.css">
    </head>
    <% List<Product> products = (List<Product>) request.getSession().getAttribute("products");%>
    <% if (products == null) {
            products = new ArrayList<Product>();
        }
    %>
    <% String msg = String.valueOf(request.getSession().getAttribute("msg"));%>
    <% if (msg == null || msg.equals("null"))
            msg = "";%>
    <body>
        <h1>Products</h1>
        <div style="background-color: red; color: white;">
            <p><%=msg%></p>
        </div>
        <br/>
        <button name="newProduct" onclick="window.location.href = 'addProduct.jsp'">New Product</button>
        <br/><br/>
        <form method="get" action="../products">
            <table>
                <tr>
                    <td class="firstColumn">Id:</td>
                    <td><input type="text" name="id"/></td>
                </tr>
                <tr><td class="firstColumn">Description:</td>
                    <td><input type="description" name="description"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="action" value="Filter" /></td>
                </tr>
            </table>            
        </form>

        <br/><br/>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Description</th>
                <th>Price</th>
                <th>Action</th>
            </tr>
            <%for (Product product : products) {%>
            <tr>
                <th><%=product.getId()%></th>
                <th><%=product.getDescription()%></th>
                <th>$<%=product.getPrice()%></th>
                <th>
                    <a href="../products?action=Delete&id=<%=product.getId()%>">Delete</a>
                    <a href="../products?action=Get&id=<%=product.getId()%>">View</a>
                </th>
            </tr>
            <%}%>
        </table>
        <br/>
        <hr/>
        <a href="../index.jsp">Back to Main</a>
    </body>
</html>
