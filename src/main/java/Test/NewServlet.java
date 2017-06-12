/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.mycompany.server.Entity.Address_matrix;
import com.mycompany.server.bean.DeliveryBeanCRUD;
import com.mycompany.server.ejb.AddressRemote;
import com.mycompany.server.ejb.Address_matrixRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    @PersistenceContext
    EntityManager em;
    
    @EJB
    AddressRemote addressBean;
    
    @EJB
    Address_matrixRemote matrixBean;
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
            //Address_matrix v = (Address_matrix) em.createQuery("SELECT a From Address_matrix a WHERE a.address1 = :a1 AND a.address2 = :a2 ").setParameter("a1", addressBean.getAddressByName("ул. Муканова 167, Алматы")).setParameter("a2", addressBean.getAddressByName("улица Казыбек Би 155, Алматы")).getSingleResult();
            /*Long id = null;
            try{
            id = (Long) em.createNativeQuery("SELECT id FROM address_matrixs WHERE fadd_id = 15651 AND sadd_id = 15651 ").getSingleResult();
            
            }catch(Exception e){e.printStackTrace();}
            System.out.println(id);*/
           Long l = addressBean.getAddressByName("ул. Муканова 167, Алматы").getId();
            System.out.println(l);
            
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
        processRequest(request, response);
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
