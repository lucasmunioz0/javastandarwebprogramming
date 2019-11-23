<%@page import="com.eduit.javaseweb.integrator.models.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
        <style>
            .firstColumn{
                text-align: right;
            }
            .hidden{
                display: none;
            }
            .notHidden p, .notHidden{
                display: inline;
                background-color: red;
                color: white;
            }
        </style>
    </head>
    <body>
        <h1>Update Product!</h1>
        <% Product product = (Product) request.getSession().getAttribute("product");%>
        <% String msg = String.valueOf(request.getSession().getAttribute("msg"));%>
        <% String divClass = "hidden";%>
        <% if (msg == null || msg.equals("null")) {
                msg = "";
                divClass = "hidden";
            } else {
                divClass = "notHidden";
            }
        %>
        <div class="<%=divClass%>">
            <p><%=msg%></p>
            <p id="demo"></p>
        </div>
        <form method="post" action="../products">
            <table>
                <tr>
                    <td class="firstColumn">Id Product:</td>
                    <td><input style="background-color: grey; color: white" id="id" type="number" name="id" value="<%=product.getId()%>" readonly/></td>
                </tr>
                <tr>
                    <td class="firstColumn">Description:</td>
                    <td><input id="description" type="text" name="description" value="<%=product.getDescription()%>"/></td>
                </tr>
                <tr>
                    <td class="firstColumn">Price: $</td>
                    <td><input id="price" type="text" name="price" value="<%=product.getPrice()%>"/></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="action" value="Update"/>
                        <input type="reset"  name="action" value="Reset"/>
                    </td>
                </tr>
            </table>
        </form>
        <br/>
        <hr/>
        <a href="products.jsp">Back</a>
    </body>
</html>