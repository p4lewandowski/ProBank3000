package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ProBank3000_GUI.fxml"));
        primaryStage.setTitle("ProBank3000");
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        DB_CONNECTOR db = new DB_CONNECTOR();
        // Check if possible to establish connection
        if (db.Connect()) {
            db.Close_Connection();
            launch(args);
        }
    }
}