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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookingController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "DisplayFirstListController";
        try {
            HttpSession session = request.getSession();
            UserDto dto =  (UserDto) session.getAttribute("INFO");
            int userId = dto.getId();
            int resourceId = Integer.parseInt(request.getParameter("txtResourceId"));
            int numberRentalDay = Integer.parseInt(request.getParameter("numberRental"));
            
            RentalDto rDto =  new RentalDto(resourceId, userId, numberRentalDay, "new");
            RentalDao dao = new RentalDao();
            if(dao.createRent(rDto)){
                request.setAttribute("NOTIFY", "Success to booking");
            }else{
                request.setAttribute("ERROR", "Fail to booking");
            }
        } catch (Exception e) {
            log("ERROR at BookingController: " + e.getMessage());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

}
