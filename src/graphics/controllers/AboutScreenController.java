/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author IEEE Gram
 */
public class AboutScreenController implements Initializable {

    @FXML
    private Pane About_Backgroud;
    @FXML
    private Label uptime_label;
    @FXML
    private Button btn_Exit;
    @FXML
    private Label window_title;
    @FXML
    private Label main_text;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SetBackground();
    }    
    public boolean SetBackground(){
        String CSSCode = "-fx-background-color: #FFFFFF;";
        About_Backgroud.setStyle(CSSCode);
        return true; 
    }

    @FXML
    private void btn_exit_Action(ActionEvent event) {
        Stage this_stage = (Stage) btn_Exit.getScene().getWindow();
        this_stage.hide();
    }
}
