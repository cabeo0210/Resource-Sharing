/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.UserDao;
import dtos.UserDto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateController extends HttpServlet {
    private static final String ERROR = "create.jsp";
    private static final String SUCCESS = "index.jsp";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String userid = request.getParameter("userid");
            String password = request.getParameter("password");
            String address = request.getParameter("address");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String role = request.getParameter("role");
            String status = request.getParameter("status");
            UserDto dto = new UserDto(userid, password, name, phone, address, status, role);
            UserDao dao = new UserDao();
            HttpSession session = request.getSession();
            boolean checkUserID = dao.checkUserId(userid);
            if (checkUserID) {
                request.setAttribute("ERROR", "Your UserID is existed");
            } else {
                boolean checkCreate = dao.createAccount(dto);
                if (checkCreate) {
                    request.setAttribute("NOTIFY", "Create success");
                    session.setAttribute("INFO", dto);
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("ERROR at CreateController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);}}
}
