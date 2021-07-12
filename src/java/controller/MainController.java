/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author haudq
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String SEARCH = "SearchController";
    private static final String CREATE = "CreateController";
    private static final String CONFIRM = "ConfirmController";
    private static final String PAGING = "PagingController";
    private static final String DASHBOARD = "DisplayFirstListController";
    private static final String LOGOUT = "LogoutController";
    private static final String BOOKING = "BookingController";
    private static final String REQUEST =  "RequestController";
    private static final String PAGING_REQUEST = "PagingRequestController";
    private static final String SEARCH_REQUEST =  "SearchRequestController";
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String action = request.getParameter("btnAction");
            if (action.equals("Login") || action.equals("Continue to login")) {
                url = LOGIN;
            } else if (action.equals("Search")) {
                url = SEARCH;
            } else if (action.equals("Create")) {
                url = CREATE;
            }else if(action.equals("Continue")){
                url = CONFIRM;
            }else if(action.equals("previous") || action.equals("next")){
                url = PAGING;
            }else if(action.equals("Dashboard")){
                url = DASHBOARD;
            }else if(action.equals("Logout")){
                url = LOGOUT;
            }else if(action.equals("Booking")){
                url = BOOKING;
            }else if(action.equals("requestController") || action.equals("requestHistory")){
                url = REQUEST;
            }else if(action.equals("preRequest") || action.equals("nextRequest")){
                url = PAGING_REQUEST;
            }
            else if(action.equals("searchRequest")){
                url = SEARCH_REQUEST;
            }
        } catch (Exception e) {
            log("Error at MainController : " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

}
