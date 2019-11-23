package com.eduit.javaseweb.integrator.controllers;

import com.eduit.javaseweb.integrator.dao.ProductDao;
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
        switch (request.getParameter("action")) {
            case "Add" : case "Update":
                doPost(request, response);
                break;
            case "Delete":
                doDelete(request, response);
                break;
            case "Get":
                doGet(request, response);
                break;
            default:
                doGetAll(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProduct = request.getParameter("id") != null && request.getParameter("id").trim().length() > 0
                    ? Integer.parseInt(request.getParameter("id")) : 0;
            String description = request.getParameter("description");
            String price = request.getParameter("price");

            Product product = new Product(idProduct, Double.parseDouble(price), description);
            String msg = insertOrUpdate(product);

            List<Product> products = dao.getAll();
            request.getSession().setAttribute("msg", msg.concat(" succesfully with Id: ") + product.getId());
            request.getSession().setAttribute("products", products);
            response.sendRedirect("integrator/products.jsp");
        } catch (IllegalArgumentException | SQLException ex) {
            request.getSession().setAttribute("msg", ex.getMessage());
            response.sendRedirect("integrator/addProduct.jsp");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = null;
        try {
            products = dao.getAll();
            Product product = new Product();
            String id = request.getParameter("id");
            product.setId(Integer.parseInt(id));
            dao.delete(product);
            products.remove(product);
            request.getSession().setAttribute("msg", "Successfully deleted");
        } catch (IllegalArgumentException | SQLException ex) {
            request.getSession().setAttribute("msg", ex.getMessage());
        } finally {
            request.getSession().setAttribute("products", products);
            response.sendRedirect("integrator/products.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idProduct = request.getParameter("id") != null && request.getParameter("id").trim().length() > 0
                    ? Integer.parseInt(request.getParameter("id")) : 0;
            Product product = new Product();
            product.setId(idProduct);
            product = dao.getById(product);
            request.getSession().setAttribute("product", product);
        } catch (IllegalArgumentException | SQLException ex) {
            request.getSession().setAttribute("msg", ex.getMessage());
        } finally {
            response.sendRedirect("integrator/editProduct.jsp");
        }
    }

    private void doGetAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<Product> products = dao.getAll();
            request.getSession().setAttribute("products", products);
        } catch (IllegalArgumentException | SQLException ex) {
            request.getSession().setAttribute("msg", ex.getMessage());
        } finally {
            response.sendRedirect("integrator/addProduct.jsp");
        }
    }

    private String insertOrUpdate(Product product) throws SQLException {
        String action = "Insert";
        if (product.getId() > 0) {
            dao.update(product);
            action = "Update";
        } else {
            dao.insert(product);
        }
        return action;
    }
}
