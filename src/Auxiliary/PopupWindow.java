package Auxiliary;

import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.*;


public class PopupWindow {

    public static void display_error()
    {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setResizable(false);
        popupwindow.getIcons().add(new Image(PopupWindow.class.getResourceAsStream( "../GraphicalAid/error_icon.png" )));

        popupwindow.setTitle("Error");
        Label label1= new Label("Wrong parameters entered");
        Button button1= new Button("close");


        button1.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 160, 60);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

    public static boolean display_confirmation()
    {
        MutableBool answer = new MutableBool();
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setResizable(false);
        popupwindow.getIcons().add(new Image(PopupWindow.class.getResourceAsStream( "../GraphicalAid/confirmation_icon.ico" )));

        popupwindow.setTitle("Confirmation");
        Label label1= new Label("Do you want to proceed?");
        Button buttonY= new Button("Yes");
        Button buttonN= new Button("No");

        buttonY.setOnAction(e -> {
            answer.setValue(true);
            popupwindow.close();
        });

        buttonN.setOnAction(e -> {
            answer.setValue(false);
            popupwindow.close();
        });

        HBox layoutH = new HBox(10);
        layoutH.getChildren().addAll(buttonY, buttonN);
        layoutH.setAlignment(Pos.CENTER);
        VBox layoutV = new VBox (10);
        layoutV.getChildren().addAll(label1, layoutH);
        layoutV.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layoutV, 150, 100);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

        return answer.getValue();
    }

    public static void display_information()
    {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setResizable(false);
        popupwindow.getIcons().add(new Image(PopupWindow.class.getResourceAsStream( "../GraphicalAid/information_icon.png" )));

        popupwindow.setTitle("Information");
        Label label1= new Label("No changes to the\ndatabase were made.");
        Button button1= new Button("close");


        button1.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 160, 100);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

    public static void display_acceptance()
    {
        Stage popupwindow=new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setResizable(false);
        popupwindow.getIcons().add(new Image(PopupWindow.class.getResourceAsStream( "../GraphicalAid/acceptance_icon.png" )));

        popupwindow.setTitle("Confirmation");
        Label label1= new Label("Process accomplished.");
        Button button1= new Button("close");


        button1.setOnAction(e -> popupwindow.close());

        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 160, 60);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }

}
