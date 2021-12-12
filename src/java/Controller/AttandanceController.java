/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import dal.AttandanceDBContext;
import dal.ClassDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Attandance;
import model.DetailAttandance;
import model.Student;

/**
 *
 * @author Do Phong PC
 */
public class AttandanceController extends HttpServlet {

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
        String groupid = (String) request.getAttribute("groupid");

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
        String clid = "";
        Cookie[] a = request.getCookies();
        for (Cookie cookie : a) {
            if (cookie.getName().equals("id")) {
                clid = cookie.getValue();
            }
        }
        String[] ids = request.getParameterValues("id");
        ArrayList<Attandance> atts = new ArrayList<>();
        for (String id : ids) {
            Attandance at = new Attandance();
            Student s = new Student();
            s.setStuID(Integer.parseInt(id));
            at.setStudent(s);
            at.setClassid(Integer.parseInt(clid));
            at.setPresent(request.getParameter("present" + id) != null);
            atts.add(at);
        }
        AttandanceDBContext db = new AttandanceDBContext();
        ArrayList<Attandance> isExist = (ArrayList<Attandance>) db.getClassId(clid);
        if (isExist.size() < 0) {
            db.insert(atts);
            ClassDBContext clDB = new ClassDBContext();
            ArrayList<DetailAttandance> detailAt = (ArrayList<DetailAttandance>) clDB.getDetailAttandanceClass(clid);
            if (detailAt.size() > 0) {
                request.setAttribute("detailAt", detailAt);
                request.getRequestDispatcher("/attandance/detail.jsp").forward(request, response);
            }
        } else {
            response.getWriter().println("Slot này đã được điểm danh");
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
