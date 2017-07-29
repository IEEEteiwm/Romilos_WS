/*
 * Copyright 2017 IEEE Gram.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package graphics.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author IEEE Gram
 */
public class HelpScreenController implements Initializable {

    @FXML
    private Pane image1_pane;
    @FXML
    private Pane image2_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        set_photos();
    }    
    
    public void set_photos(){
        String image1 = "-fx-background-image: url('file://../res/images/logo_banner.png');";
        String image2 = "-fx-background-image: url('file://../res/images/teiwm_logo_new.jpg');";
        image1_pane.setStyle(image1);
        image2_pane.setStyle(image2);
    }
}
