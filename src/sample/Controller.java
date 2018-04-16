package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;


public class Controller {

    @FXML private TextField add_n;
    @FXML private TextField add_sn;
    @FXML private TextField add_c;
    @FXML private TextField add_cst;
    @FXML private TextField add_cn;
    @FXML private TextField add_p;
    @FXML private TextField add_num;
    @FXML private TextField add_f;


    DB_CONNECTOR db = new DB_CONNECTOR();

    public Controller() {
        System.out.println("Initializing db connection \n");
        db.Connect();

    }

    @FXML
    private void show_all(ActionEvent event) {
        db.Show_All();
    }

    @FXML
    private void add_user(ActionEvent event) throws SQLException {
        db.Add_User(add_n.getText(), add_sn.getText(), add_c.getText(), add_cst.getText(),
                add_cn.getText(),add_p.getText(), add_num.getText(), add_f.getText());
    //Integer.parseInt(

    }
}
