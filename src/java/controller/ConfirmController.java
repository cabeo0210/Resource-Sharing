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


public class ConfirmController extends HttpServlet {
    private static final String ERROR = "confirm.jsp";
    private static final String SUCCESS = "DisplayFirstListController";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            HttpSession session = request.getSession();

            int clientCode = Integer.parseInt(request.getParameter("codeEmail"));
            int code = (int) session.getAttribute("CODE");
            UserDao dao = new UserDao();

            if (code == clientCode) {
                UserDto user = (UserDto) session.getAttribute("INFO");
                if (dao.activeAccount(user.getUserId())) {
                    url = SUCCESS;
                    session.removeAttribute("CODE");
                } else {
                    request.setAttribute("ERROR", "Have some problem!");
                }
            } else {
                request.setAttribute("ERROR", "Your code is invalid");
            }
        } catch (Exception e) {
            log("ERROR at ConfirmController: " + e.getMessage());
        } finally {request.getRequestDispatcher(url).forward(request, response);}}}
