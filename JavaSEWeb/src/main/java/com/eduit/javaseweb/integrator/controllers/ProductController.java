package com.eduit.javaseweb.integrator.controllers;

import com.eduit.javaseweb.integrator.dao.ProductDao;
import com.eduit.javaseweb.integrator.exceptions.BusinessException;
import com.eduit.javaseweb.integrator.models.Product;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductController", urlPatterns = {"/products"})
public class ProductController extends HttpServlet {

    private static ProductDao dao = new ProductDao();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        switch (request.getParameter("action")) {
            case "Add":
            case "Update":
                doPost(request, response);
                break;
            case "Delete":
                doDelete(request, response);
                break;
            case "Get":
                doGet(request, response);
                break;
            case "Filter":
                doFilter(request, response);
                break;
            case "New":
                doNew(request, response);
                break;
            default:
                doGetAll(request, response, "", false);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = null;
        try {
            final int idProduct = request.getParameter("id") != null && request.getParameter("id").trim().length() > 0
                    ? Integer.parseInt(request.getParameter("id")) : 0;
            final String description = request.getParameter("description");
            final String price = request.getParameter("price");

            product = new Product(idProduct, Double.parseDouble(price), description);
            final String msg = insertOrUpdate(product).concat(" succesfully with Id: ") + product.getId();
            doGetAll(request, response, msg, false);
        } catch (IOException | NumberFormatException | BusinessException | SQLException ex) {
            if (request.getParameter("action").equals("Update")) {
                request.getSession().setAttribute("product", product);
                redirect(request, response, "integrator/editProduct.jsp", ex.toString(), true);
            } else {
                redirect(request, response, "integrator/addProduct.jsp", ex.toString(), true);
            }
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "";
        try {
            final int idProduct = request.getParameter("id") != null && request.getParameter("id").trim().length() > 0
                    ? Integer.parseInt(request.getParameter("id")) : 0;
            final Product product = new Product(idProduct);
            dao.delete(product);
            msg = "Successfully deleted";
        } catch (NumberFormatException | BusinessException | SQLException ex) {
            msg = ex.toString();
        } finally {
            doGetAll(request, response, msg, !msg.equals("Successfully deleted"));
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProduct = request.getParameter("id") != null && request.getParameter("id").trim().length() > 0
                    ? Integer.parseInt(request.getParameter("id")) : 0;
            final Product product = dao.getById(new Product(idProduct));
            request.getSession().setAttribute("product", product);
            redirect(request, response, "integrator/editProduct.jsp", "", false);
        } catch (IOException | NumberFormatException | BusinessException | SQLException ex) {
            doGetAll(request, response, ex.toString(), true);
        }
    }

    private void doGetAll(HttpServletRequest request, HttpServletResponse response, String msg, boolean isError) throws IOException {
        try {
            final List<Product> products = dao.getAll();
            request.getSession().setAttribute("products", products);
            //redirect(request, response, "integrator/products.jsp", msg, isError);
        } catch (SQLException ex) {
            msg = ex.toString();
            isError = true;
        }finally{
            redirect(request, response, "integrator/products.jsp", msg, isError);
        }
    }

    private String insertOrUpdate(Product product) throws SQLException, BusinessException {
        String action = "Insert";
        if (product.getId() > 0) {
            dao.update(product);
            action = "Update";
        } else {
            dao.insert(product);
        }
        return action;
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String id = request.getParameter("id");
        final String description = request.getParameter("description");
        String msg = "";
        try {
            final Integer integer = id != null && !id.trim().equals("") ? Integer.parseInt(id) : null;
            if (integer != null) {
                doGet(request, response);
                return;
            }
            final List<Product> products = dao.getProductsByDescription(description);
            request.getSession().setAttribute("products", products);
        } catch (Exception ex) {
            msg = ex.toString();
        }
        redirect(request, response, "integrator/products.jsp", msg, !msg.isEmpty());
    }

    private void redirect(HttpServletRequest request, HttpServletResponse response, String resource, String msg, boolean isError) throws IOException {
        if (isError) {
            request.getSession().setAttribute("error", msg);
            request.getSession().setAttribute("msg", null);
        } else {
            request.getSession().setAttribute("error", null);
            request.getSession().setAttribute("msg", msg);
        }
        response.sendRedirect(resource);
    }

    private void doNew(HttpServletRequest request, HttpServletResponse response) throws IOException {
        redirect(request, response, "integrator/addProduct.jsp", "", false);
    }
}
