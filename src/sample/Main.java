package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ProBank3000_GUI.fxml"));
        primaryStage.setTitle("ProBank3000");
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream( "App_logo.png" )));

        //primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.show();

    }

    public static void main(String[] args) {
            launch(args);
    }
}