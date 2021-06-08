/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import static pi.devjava.controller.interfaceResponsableController.loadWindow;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class utController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void add(ActionEvent event) {
        
          loadWindow(getClass().getResource("/pi/devjava/GUI/addUser.fxml"), "responsable", null);
    }
    

    @FXML
    private void delete(ActionEvent event) {
                  loadWindow(getClass().getResource("/pi/devjava/GUI/afficheUser.fxml"), "responsable", null);

    }
    
}
