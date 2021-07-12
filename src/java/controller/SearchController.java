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

public class SearchController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String url = "search.jsp";
        try {
            String category = request.getParameter("category");
            String name = request.getParameter("name");
            String action = request.getParameter("btnAction");
            int usingDate = 0;
            if (request.getParameter("usingDate") != null && !request.getParameter("usingDate").isEmpty()) {
                usingDate = Integer.parseInt(request.getParameter("usingDate"));
            }
            if (category == null && name == null) {
                category = "";
                name = "";
            }
            HttpSession session = request.getSession();
            int offsetPage = (int) session.getAttribute("offsetPage");
            int nextPage = (int) session.getAttribute("nextPage");
            if (action.equals("Search")) {
                offsetPage = 0;
                nextPage = 21;}
            ResourceDao dao = new ResourceDao();
                List<ResourceDto> listResource = dao.searchResource(category, name, usingDate, offsetPage, nextPage);
                List<String> listCategory = dao.getListCategory();
                request.setAttribute("CATEGORYS", listCategory);
                request.setAttribute("RESOURCES", listResource);
        } catch (Exception e) {
            log("Error at SearchController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);}}

}
