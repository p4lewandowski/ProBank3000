package sample;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

class DB_CONNECTOR {

    @FXML private TextField add_n;

    private Connection conn;

    public Connection Get_Connection() {
        return conn;
    }

    public boolean Connect() {
        String dbURL = "jdbc:oracle:thin:db_crea/gaderypoluki@194.182.69.12:1521:xe";
        //DriverManager.registerDriver(new oracle.jdbc.OracleDriver()); optional

        try {
            //create  the connection object
            conn = DriverManager.getConnection(dbURL);
            if (conn != null) {
                System.out.println("Connected");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(128);
            return false;
        }
        return true;
    }



    public void Close_Connection(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(128);}
    }

    public ResultSet Show_All(){
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from bank_users");
            return rs;
        } catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void Add_User(String NAME, String SURNAME,String ADDRESS_CITY,String ADDRESS_STREET, String ADDRESS_NUMBER,
                         String PESEL, String PHONE_NUMBER, String FUNDS) throws SQLException{
        Statement stmt = conn.createStatement();
        String selectQuery = "INSERT INTO BANK_USERS (NAME, SURNAME, ADDRESS_CITY, ADDRESS_STREET," +
                    " ADDRESS_NUMBER, PESEL, PHONE_NUMBER, FUNDS) VALUES ('" + NAME + "', '" + SURNAME + "', '"
                + ADDRESS_CITY + "', '" + ADDRESS_STREET + "', '" + ADDRESS_NUMBER + "', "
                + PESEL + ", '" + PHONE_NUMBER + "', " + FUNDS + ")";
        //System.out.println(selectQuery);
        ResultSet rs = stmt.executeQuery(selectQuery);

    }

    public void DeleteUser(String delID, String delPESEL) throws SQLException{
        Statement stmt = conn.createStatement();
        String selectQuery = null;
        if(delID != null && !delID.isEmpty()) {
            selectQuery = "DELETE FROM BANK_USERS WHERE USER_ID = " + delID;
        }
        if(delPESEL != null && !delPESEL.isEmpty()) {
            selectQuery = "DELETE FROM BANK_USERS WHERE PESEL = " + delPESEL;
        }
        stmt.executeQuery(selectQuery);
    }

    public void WithdrawMoney(String uID, String uFunds) throws SQLException{
        Statement stmt = conn.createStatement();
        String selectQuery =  "UPDATE BANK_USERS SET FUNDS = FUNDS - " + uFunds
                + " WHERE USER_ID = " + uID;
        stmt.executeQuery(selectQuery);
    }

    public void DepositMoney(String uID, String uFunds) throws SQLException{
        Statement stmt = conn.createStatement();
        String selectQuery =  "UPDATE BANK_USERS SET FUNDS = FUNDS + " + uFunds
                + " WHERE USER_ID = " + uID;
        stmt.executeQuery(selectQuery);
    }

    public void TransferMoney(String uIDs, String uIDr, String uFunds) throws SQLException{
        Statement stmt = conn.createStatement();
        Long availableFunds = null;
        String selectQueryS =  "SELECT FUNDS FROM BANK_USERS WHERE USER_ID = " + uIDs;
        ResultSet rs = stmt.executeQuery(selectQueryS);
        while (rs.next()) {
            availableFunds = Long.parseLong(rs.getString(1), 10);
        }
        if(availableFunds<Long.parseLong(uIDs, 10))
        {
            throw new java.sql.SQLException();
        }
        else
        {
            String selectQuery =  "UPDATE BANK_USERS SET FUNDS = FUNDS + " + uFunds
                    + " WHERE USER_ID = " + uIDr;
            stmt.executeQuery(selectQuery);
            selectQuery =  "UPDATE BANK_USERS SET FUNDS = FUNDS - " + uFunds
                    + " WHERE USER_ID = " + uIDs;
            stmt.executeQuery(selectQuery);
        }
    }
}
