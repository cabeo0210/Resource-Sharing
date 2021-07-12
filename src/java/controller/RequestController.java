/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.RentalDao;
import dtos.RentalDto;
import dtos.UserDto;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RequestController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "request.jsp";
        try {
            String action = request.getParameter("btnAction");
            RentalDao dao = new RentalDao();
            HttpSession session = request.getSession();
            List<RentalDto> listRequest = new ArrayList<>();
            UserDto user = (UserDto) session.getAttribute("INFO");
            String userId = user.getUserId();
            if (user.getRole().equals("manager")) {
                userId = "";
            } 
            switch (action) {
                case "requestHistory":
                    session.setAttribute("offsetPage", 0);
                    session.setAttribute("nextPage", 21);
                    listRequest = dao.searchRentHistory(userId, "", "", "", 0, 20);
                    request.setAttribute("REQUESTS", listRequest);
                    session.setAttribute("ACTION", "requestHistory");
                    break;
                case "requestController":
                    session.setAttribute("offsetPage", 0);
                    session.setAttribute("nextPage", 21);
                    listRequest = dao.searchRentHistory(userId, "", "", "", 0, 20);
                    request.setAttribute("REQUESTS", listRequest);
                    session.setAttribute("ACTION", "requestController");
                    break;
                case "approve": {
                    int id = Integer.parseInt(request.getParameter("rentId"));
                    boolean check = dao.updateStatusByRentId("accepted", id);
                    if (check) {
                        request.setAttribute("NOTIFY", "Approve successful");
                    } else {
                        request.setAttribute("ERROR", "Approve fail");
                    }
                    int offset = (int) session.getAttribute("offsetPage");
                    int next = (int) session.getAttribute("nextPage");
                    listRequest = dao.searchRentHistory(userId, "", "", "", offset, next);
                    request.setAttribute("REQUESTS", listRequest);
                    break;
                }
                case "reject": {
                    int id = Integer.parseInt(request.getParameter("rentId"));
                    boolean check = dao.updateStatusByRentId("rejected", id);
                    if (check) {
                        request.setAttribute("NOTIFY", "Reject successful");
                    } else {
                        request.setAttribute("ERROR", "Reject fail");
                    }
                    int offset = (int) session.getAttribute("offsetPage");
                    int next = (int) session.getAttribute("nextPage");
                    listRequest = dao.searchRentHistory(userId, "", "", "", offset, next);
                    request.setAttribute("REQUESTS", listRequest);
                    break;
                }
                case "delete": {
                    int id = Integer.parseInt(request.getParameter("rentId"));
                    boolean check = dao.updateStatusByRentId("inactive", id);
                    if (check) {
                        request.setAttribute("NOTIFY", "Delete successful");
                    } else {
                        request.setAttribute("ERROR", "Delete fail");
                    }
                    int offset = (int) session.getAttribute("offsetPage");
                    int next = (int) session.getAttribute("nextPage");
                    listRequest = dao.searchRentHistory(userId, "", "", "", offset, next);
                    request.setAttribute("REQUESTS", listRequest);
                    break;
                }
                default:
                    break;
            }

        } catch (Exception e) {
            log("ERROR at RequestController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }
}
