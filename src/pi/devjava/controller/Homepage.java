/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class Homepage extends Application{  
    
    @Override
    public void start(Stage stage) throws Exception { 
        Parent root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/FrontGarderie.fxml"));
        Scene scene =new Scene(root,930,570) ; 
        stage.setScene(scene); 
        stage.show();  
   
    }  
    
    public static void main(String[] args) 
    { 
        launch(args);
    }
}

   
