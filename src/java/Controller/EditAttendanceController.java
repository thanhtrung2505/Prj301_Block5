/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.DetailAttendanceDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DetailAttendance;
import model.Student;

/**
 *
 * @author Do Phong PC
 */
public class EditAttendanceController extends HttpServlet {

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
            out.println("<title>Servlet EditAttendanceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditAttendanceController at " + request.getContextPath() + "</h1>");
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
        ArrayList<DetailAttendance> s = DB.getDetailAttandanceEdit(request.getParameter("classid"), request.getParameter("stuid"));
        if (s.size() < 0) {
            response.getWriter().println("error");
        } else {
            request.setAttribute("edit", s);
            request.getRequestDispatcher("/attandance/edit.jsp").forward(request, response);
        }
    }
    DetailAttendanceDBContext DB = new DetailAttendanceDBContext();

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
        DetailAttendance s = new  DetailAttendance();
        s.setClassid(Integer.parseInt(request.getParameter("classid")));      
        s.setStuid(Integer.parseInt(request.getParameter("stuid")));    
        s.setPresent(request.getParameter("present")!=null);  
        DetailAttendanceDBContext deDB = new DetailAttendanceDBContext();
        deDB.update(s);      
        response.sendRedirect("listdetail");
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
