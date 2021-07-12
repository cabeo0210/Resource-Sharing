package daos;

import dtos.RentalDto;
import util.MyConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RentalDao implements Serializable {

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

    public boolean updateStatusByRentId(String status, int rentId) throws Exception {
        boolean result = false;
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE [dbo].[Rent] SET [Status] = ? WHERE [Id] =  ?");
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, rentId);
            result = preparedStatement.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<RentalDto> searchRentHistory(String userId, String name, String requestDate, String status, int current, int next) throws Exception {
        List<RentalDto> result = new ArrayList<>();
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("select r.Name, u.Name, re.RentDate, re.Status, re.Id\n"
                    + "from (([dbo].[Rent] as re join [dbo].[Resource] as r on re.ResourceId = r.Id) join [dbo].[User] as u on re.UserId = u.Id) where re.RentDate like ? and r.Name like ? and re.Status like ? and u.UserID like ?\n"
                    + "Order by re.RentDate desc\n"
                    + "OFFSET ? ROWS\n"
                    + "FETCH NEXT ? ROWS ONLY");
            preparedStatement.setString(1, "%" + requestDate + "%");
            preparedStatement.setString(2, "%" + name + "%");
            preparedStatement.setString(3, "%" + status + "%");
            preparedStatement.setString(4, "%" + userId + "%");
            preparedStatement.setInt(5, current);
            preparedStatement.setInt(6, next);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String resourceName = resultSet.getString(1);
                String userName = resultSet.getString(2);
                Date date = resultSet.getDate("RentDate");
                String statusRequest = resultSet.getString("Status");
                int rentId = resultSet.getInt(5);
                RentalDto dto = new RentalDto(rentId, resourceName, userName, date, statusRequest);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean createRent(RentalDto dto) throws Exception {
        boolean result = false;
        try {
            connection = MyConnection.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO [dbo].[Rent]([ResourceId], [UserId], [NumberOfRentalDay], [Status]) VALUES(?,?,?,?)");
            preparedStatement.setInt(1, dto.getResourceId());
            preparedStatement.setInt(2, dto.getUserId());
            preparedStatement.setInt(3, dto.getNumberRentalDay());
            preparedStatement.setString(4, dto.getStatus());
            result = preparedStatement.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
