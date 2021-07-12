/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.ResourceDto;
import util.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haudq
 */
public class ResourceDao implements Serializable {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private void closeConnection() throws Exception {
        if (resultSet != null) {
            connection.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    
    public List<String> getListCategory() throws Exception{
        List<String> result  =  new ArrayList<>();
        try {
            connection =MyConnection.getConnection();
            preparedStatement = connection.prepareCall("SELECT [CategoryName] FROM [dbo].[Category]");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                String name =  resultSet.getString("CategoryName");
                result.add(name);
            }
        } finally{
            closeConnection();
        }
        return result;
    }
    public List<ResourceDto> searchResource(String category, String name, int usingDate, int current, int next) throws Exception {
        List<ResourceDto> result = new ArrayList<>();
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("SELECT r.Id, r.Name, r.Color, r.Description, c.CategoryName, c.CategoryDescription, r.Quantity, r.UsingDate FROM ([dbo].[Resource] as r join [dbo].[Category] as c\n"
                    + "on r.CategoryId = c.Id) Where c.CategoryName like ? and r.Name like ?  and r.UsingDate >= ?\n"
                    + "ORDER BY r.Name\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY\n"
                    + "");
            preparedStatement.setString(1, "%"+category+"%");
            preparedStatement.setString(2, "%"+name+"%");
            preparedStatement.setInt(3, usingDate);
            preparedStatement.setInt(4, current);
            preparedStatement.setInt(5, next);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id =  resultSet.getInt("Id");
                String nameResource = resultSet.getString("Name");
                String color = resultSet.getString("Color");
                String description = resultSet.getString("Description");
                int quantity = resultSet.getInt("Quantity");
                int usingDateResource = resultSet.getInt("UsingDate");
                String categoryName = resultSet.getString("CategoryName");
                String categoryDescription = resultSet.getString("CategoryDescription");

                ResourceDto dto = new ResourceDto(id, nameResource, color, quantity, usingDateResource, description, categoryName, categoryDescription);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
