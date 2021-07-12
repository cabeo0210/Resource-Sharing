/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.ResourceDao;
import dtos.ResourceDto;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DisplayFirstListController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = "";
            String category = "";
            int usingDate = 0;
            HttpSession session = request.getSession();
            session.setAttribute("offsetPage", 0);
            session.setAttribute("nextPage", 21);

            ResourceDao dao = new ResourceDao();
            List<ResourceDto> listResource = dao.searchResource(category, name, usingDate, 0, 21);
            List<String> listCategory = dao.getListCategory();
            
            request.setAttribute("RESOURCES", listResource);
            request.setAttribute("CATEGORYS", listCategory);
        } catch (Exception e) {
            log("Error at DisplayFirstController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("search.jsp").forward(request, response);
        }
    }

}
