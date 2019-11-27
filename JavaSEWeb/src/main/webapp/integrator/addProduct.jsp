<%@page import="com.eduit.javaseweb.integrator.models.Product"%>
<!DOCTYPE html>
<html>
    <head>
        <!--meta http-equiv="Content-Type" content="text/html; charset=UTF-8"-->
        <title>Product</title>
        <link rel="stylesheet" href="../css/styles.css">
    </head>
    <body>
        <% Product product = (Product) request.getSession().getAttribute("product");
            String msg = String.valueOf(request.getSession().getAttribute("msg"));
            String error = String.valueOf(request.getSession().getAttribute("error"));
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
                    Back<a href="../products?action=GetAll"><img src="../resources/images/volver.png" width="25" height="25" alt="Volver"/></a>
                </th>
            </table>
        </div>
        <fieldset class="wrapper">
            <legend>Add new Product</legend>
            <%if (error != null && !error.equals("null") && !error.isEmpty()) {
                    out.print("<div class=\"error\"><p>" + error + "</p></div>");
                } else if (msg != null && !msg.equals("null") && !msg.isEmpty()) {
                    out.print("<div class=\"sucess\"><p>" + msg + "</p></div>");
                }%>
            <form method="post" action="../products">
                <table>
                    <tr>
                        <td class="firstColumn">Id Product:</td>
                        <td><input type="number" name="id"/><label>&nbsp;For insert, please do not insert a value.</label></td>
                    </tr>
                    <tr>
                        <td class="firstColumn">Description:</td>
                        <td><input type="text" name="description"/></td>
                    </tr>
                    <tr>
                        <td class="firstColumn">Price: $</td>
                        <td><input type="text" name="price"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" name="action" value="Add" />
                            <input type="reset"  name="action" value="Reset"/>
                        </td>
                    </tr>
                </table>
            </form>
        </fieldset>
    </body>
</html>