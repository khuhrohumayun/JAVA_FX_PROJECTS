/*******************************************************************************
            Controller class and logic implementation for logout.fxml
 ******************************************************************************/
package com.example.cinema;

import com.jfoenix.controls.JFXButton;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class logoutController extends CommonMethods implements Initializable{
    @FXML
    private JFXButton yesbtn, nobtn, closebtn;
    @FXML
    private AnchorPane logoutpane;

    /**
     *  Initialise method required for implementing initializable and,
     *  sets up and applies all effects and animations to nodes in logout.fxml
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        page="Log Out";
        nobtn.setOnMouseEntered(e -> {
            nobtn.setStyle("-fx-background-color: #FF9A00;");
            nobtn.setEffect(new Bloom(0.8));
        });
        nobtn.setOnMouseExited(e -> {
            nobtn.setStyle("-fx-background-color:  #FF5722;");
            nobtn.setEffect(new Bloom(1));
        });

        yesbtn.setOnMouseEntered(e -> {
            yesbtn.setStyle("-fx-background-color: #FF9A00;");
            yesbtn.setEffect(new Bloom(0.8));
        });
        yesbtn.setOnMouseExited(e -> {
            yesbtn.setStyle("-fx-background-color:  #FF5722;");
            yesbtn.setEffect(new Bloom(1));
        });

        disableAllFocus(logoutpane);
        closebtn.setOnMouseEntered(e -> closebtn.setStyle("-fx-background-color:  #F6490D"));
        closebtn.setOnMouseExited(e -> closebtn.setStyle("-fx-background-color: transparent"));
        moveWindow(logoutpane);
    }

    /**
     *  Handle method that is called whenever the yes or no buttons are
     *  clicked. Plays a little transition effect on the button after
     *  it is clicked. Also, closes the logout popup window and if the yes
     *  btn is clicked, sets the static confirmed boolean to true in CommonMethods.
     * @param event
     */
    public void handleConfirmation(ActionEvent event){
        JFXButton btn=(JFXButton)event.getSource();
        TranslateTransition t1=new TranslateTransition(Duration.millis(270),btn);
        t1.setToY(-17d);
        PauseTransition p1=new PauseTransition(Duration.millis(40));
        TranslateTransition t2=new TranslateTransition(Duration.millis(270),btn);
        t2.setToY(0d);

        SequentialTransition d=new SequentialTransition(btn, t1,p1,t2);
        d.play();
        d.setOnFinished(event1 -> {
            if(btn.getId().equals("yesbtn")){
                CommonMethods.confirmed=true;
                handleClose(event);
            }else if(btn.getId().equals("nobtn")){
                CommonMethods.confirmed=false;
                handleClose(event);
            }
        });
    }
}


