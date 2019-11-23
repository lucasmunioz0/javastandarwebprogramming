package com.eduit.javaseweb.laboratories.laboratory01;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Test", urlPatterns = {"/test"})
public class Laboratory01b extends HttpServlet{
    
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
}
