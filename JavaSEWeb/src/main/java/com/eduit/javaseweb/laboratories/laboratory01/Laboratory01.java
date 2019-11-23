package com.eduit.javaseweb.laboratories.laboratory01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Laboratory01", urlPatterns = {"/Laboratory01"})
public class Laboratory01 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Laboratory01</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Laboratory01 at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");
        String residence = request.getParameter("residence");
        String country = request.getParameter("country");        
        
        try(PrintWriter pw = response.getWriter()){
            pw.println("<! DOCTYPE html>");
            pw.println("<html>");
            pw.println("<body>");
            pw.println("<h1>Laboratory 01</h1>");
            pw.println("<h2>Hi<bold>");
            pw.println(name + ", " + lastName);
            pw.println("</bold></h2>");
            pw.println("<p>Residence: " + residence);
            pw.println("</p>");
            pw.println("<p>Country: " + country);
            pw.println("</p>");
            pw.println("<br/>");
            pw.println("<hr/>");
            pw.println("<a href=\"laboratory01/index.html\">Back to Index</a>");
            pw.println("</body>");
            pw.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
