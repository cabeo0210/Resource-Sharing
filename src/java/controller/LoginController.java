/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dtos.UserDto;
import daos.UserDao;
import util.VerifyUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    private static final String SUCCESS = "DisplayFirstListController";
    private static final String FAIL = "index.jsp";
    private static final String ACTIVE = "ActiveController";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = FAIL;
        boolean flag = true;
        String errorMessage = "";
        try {
            String action = request.getParameter("btnAction");
            HttpSession session = request.getSession();
            UserDao dao = new UserDao();
            if (action.equals("Continue to login")) {
                String email = request.getParameter("email").trim();
                UserDto user = dao.loginByGoogle(email);
                user.setUserId(email);
                if (user.getRole() == null) {
                    flag = false;
                    errorMessage = "User is not found";
                } else if (user.getStatus().equals("deactive")) {
                    flag = false;
                    errorMessage = "Your account is not available";
                }
                if (!flag) {
                    request.setAttribute("ERROR", errorMessage);
                } else {
                    session.setAttribute("INFO", user);
                    if (user.getStatus().equals("new")) {
                        url = ACTIVE;
                    } else {
                        switch (user.getRole().trim()) {
                            case "manager":
                                url = SUCCESS;
                                break;
                            case "leader":
                                url = SUCCESS;
                                break;
                            case "employee":
                                url = SUCCESS;
                                break;
                            default:
                                break;
                        }
                    }
                }
            } else {
                String userID = request.getParameter("txtUserID").trim();
                String password = request.getParameter("txtPassword").trim();
                UserDto user = dao.loginByUserIdAndPassword(userID, password);
                user.setUserId(userID);
                if (user.getRole() == null) {
                    flag = false;
                    errorMessage = "User is not found";
                } else if (user.getStatus().equals("deactive")) {
                    flag = false;
                    errorMessage = "Your account is not available";
                }

                flag = true;
                if (!flag) {
                    request.setAttribute("ERROR", errorMessage);
                } else {
                    session.setAttribute("INFO", user);
                    if (user.getStatus().equals("new")) {
                        url = ACTIVE;
                    } else {
                        switch (user.getRole().trim()) {
                            case "manager":
                                url = SUCCESS;
                                break;
                            case "leader":
                                url = SUCCESS;
                                break;
                            case "employee":
                                url = SUCCESS;
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            log("ERROR at LoginController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

}
