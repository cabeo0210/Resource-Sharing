/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.sql.Date;

/**
 *
 * @author haudq
 */
public class RentalDto {

    private int id;
    private int resourceId;
    private int userId;
    private int numberRentalDay;
    private String resourceName;
    private String userName;
    private Date rentDate;
    private String status;

    public RentalDto(int resourceId, int userId, int numberRentalDay, String status) {
        this.resourceId = resourceId;
        this.userId = userId;
        this.numberRentalDay = numberRentalDay;
        this.status = status;
    }

    public RentalDto(int id, String resourceName, String userName, Date rentDate, String status) {
        this.id = id;
        this.resourceName = resourceName;
        this.userName = userName;
        this.rentDate = rentDate;
        this.status = status;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNumberRentalDay() {
        return numberRentalDay;
    }

    public void setNumberRentalDay(int numberRentalDay) {
        this.numberRentalDay = numberRentalDay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
