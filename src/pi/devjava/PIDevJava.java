/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import pi.devjava.connectionBD.DataBase;
import pi.devjava.entities.Utilisateur;

/**
 *
 * @author youssef
 */
public class PIDevJava extends Application {
    
    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;
    public static DataBase bdd = new DataBase("localhost:3306", "learn and play", "root", "");

      public void start(Stage stage) throws Exception {
        
     //Parent root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/interfaceParent.fxml"));
         Parent root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/Login.fxml"));
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
     /*
      Parent root = FXMLLoader.load(getClass().getResource("/pi/devjava/GUI/Login.fxml"));
        Scene scene =new Scene(root,930,650) ; 
        stage.setScene(scene); 
        stage.show(); */ 
   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        bdd.connection();
      launch(args);
      
    }}