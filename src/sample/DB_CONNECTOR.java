package sample;
import com.healthmarketscience.sqlbuilder.InsertQuery;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

class DB_CONNECTOR {

    @FXML private TextField add_n;

    private Connection conn;
    FXMLLoader fxmlLoader = new FXMLLoader();

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

    public void Show_All() {
        try{
            //create the statement object
            Statement stmt = conn.createStatement();
            //execute query
            ResultSet rs = stmt.executeQuery("select * from bank_users");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(128);}
    }

    public void Add_User(String NAME, String SURNAME,String ADDRESS_CITY,String ADDRESS_STREET, String ADDRESS_NUMBER,
                         String PESEL, String PHONE_NUMBER, String FUNDS) throws SQLException{
        Statement stmt = conn.createStatement();
        String selectQuery = "INSERT INTO BANK_USERS (NAME, SURNAME, ADDRESS_CITY, ADDRESS_STREET," +
                    " ADDRESS_NUMBER, PESEL, PHONE_NUMBER, FUNDS) VALUES ('" + NAME + "', '" + SURNAME + "', '"
                + ADDRESS_CITY + "', '" + ADDRESS_STREET + "', '" + ADDRESS_NUMBER + "', "
                + PESEL + ", '" + PHONE_NUMBER + "', " + FUNDS + ")";
        System.out.println(selectQuery);
        ResultSet rs = stmt.executeQuery(selectQuery);

    }
}

//    ResultSet rs = stmt.executeQuery("INSERT INTO BANK_USERS (NAME, SURNAME, ADDRESS_CITY, ADDRESS_STREET," +
//                    " ADDRESS_NUMBER, PESEL, PHONE_NUMBER, FUNDS) VALUES ('%s', '%s', '%s', '%s', '%s', %d, '%s', %d)",
//            NAME, SURNAME, ADDRESS_CITY, ADDRESS_STREET, ADDRESS_NUMBER, PESEL, PHONE_NUMBER, FUNDS);

//        ResultSet rs = stmt.executeQuery("INSERT INTO BANK_USERS (NAME, SURNAME, ADDRESS_CITY, ADDRESS_STREET," +
//                " ADDRESS_NUMBER, PESEL, PHONE_NUMBER, FUNDS) VALUES ('%s', '%s', '%s', '%s', '%s', %d, '%s', %d)",
//                "Krzysztof", "Wladzik", "Konin", "Zielona", "12 A", 952304233, "+48692346942", 134214324);