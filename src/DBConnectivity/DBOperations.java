package DBConnectivity;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;

public class DBOperations {
    DBConnection db;
    public DBOperations() throws SQLServerException {
        db = new DBConnection();
        db.Connect();
    }

    public void createTable() throws SQLException {
        String query = "CREATE TABLE Student(id INT, FirstName VARCHAR(25), SecName VARCHAR(25))";

        Statement stmt = db.getConnection().createStatement();

        stmt.execute(query);

        stmt.close();
    }

    public void dropTable(String tableName) throws SQLException {
        String query = "DROP TABLE " + tableName;

        Statement stmt = db.getConnection().createStatement();

        stmt.execute(query);

        stmt.close();

    }

    public void insert(int id, String fn, String sn) throws SQLException {
        String query = "EXEC insertIntoStudent ?,?,?";

        PreparedStatement stmt = db.getConnection().prepareStatement(query);

        stmt.setInt(1, id);
        stmt.setString(2, fn);
        stmt.setString(3, sn);

        stmt.execute();

        stmt.close();
    }

    public String update(int id, String fn) throws SQLException {
        String query = "EXEC updateStd ?,?,?";

        CallableStatement stmt = db.getConnection().prepareCall(query);

        stmt.setInt(1, id);
        stmt.setString(2, fn);

        stmt.registerOutParameter(3, Types.VARCHAR);

        stmt.execute();

        String message = stmt.getString(3);

        stmt.close();

        return message;
    }

    public ResultSet select() throws SQLException {
        String query = "SELECT * from Student";

        Statement statement = db.getConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        return resultSet;
    }


}
