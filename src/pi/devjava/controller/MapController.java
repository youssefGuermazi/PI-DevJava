/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebEvent;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import static pi.devjava.controller.addGardController.loadWindow;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class MapController implements Initializable {

    @FXML
    private WebView wv;
    @FXML
    private JFXTextField adr;
    @FXML
    private Label lc;
    static String adrGard;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
  
WebEngine webEngine = wv.getEngine();

    /*
       
        wv.getEngine()
            .getLoadWorker()
            .stateProperty()
            .addListener((obs, oldState, newState) -> {
                if (newState == Worker.State.SUCCEEDED) {
                    Document doc = wv.getEngine().getDocument();
                    showNodeContent(doc, 0);
                }
            });
       // BorderPane root = new BorderPane(wv);
        */
       //webEngine.load("http://www.jquerysample.com/#BasicAJAX");

       webEngine.load("file:///C:/Users/youssef/Downloads/map.html");
       wv.getEngine().setOnAlert(
               stringWebEvent->{System.out.println(stringWebEvent.getData());
                  try{
                      
                      URL obj = new URL(""+stringWebEvent.getData()+"&accept-language=fr");
                      HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
                  
conn.addRequestProperty("User-Agent", 
"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
                      BufferedReader in = new BufferedReader( new InputStreamReader(conn.getInputStream()));
                      String inputLine;
                      StringBuffer response = new StringBuffer();
                      while((inputLine = in.readLine()) != null ){
                          response.append(inputLine);
                      }
                      in.close();
                      JSONObject myresponse = new JSONObject(response.toString());
                      JSONObject adressObject = new JSONObject(myresponse.getJSONObject("address").toString());
                      //System.out.println(adressObject.getString("country")+" "+adressObject.getString("state"));
                       //adr.setText((adressObject.getString("country")+" "+adressObject.getString("state")));
                       adr.setText(myresponse.getString("display_name"));
                      System.out.println(myresponse.getString("display_name"));
                      adrGard=adr.getText();
                      
                      
                      
                  } catch (MalformedURLException ex) {
        Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (JSONException ex) {
        Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
    }
               
               });
      
       
       
       
      //  webEngine.loadContent("");
    }
/*
    private void showNodeContent(Node n, int depth) {
        for (int i=0; i<depth; i++) {
            System.out.print(" ");
        }
        System.out.println(n.getNodeName()+":"+n.getNodeValue());
        NodeList children = n.getChildNodes() ;
        for (int i=0; i<children.getLength(); i++) {
            showNodeContent(children.item(i), depth+1);
        }
    }

   */
        public static void loadWindow(URL loc, String title, Stage parentStage) {
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.UNDECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void retAj(MouseEvent event){
        addGardController.ad=adrGard;
           loadWindow(getClass().getResource("/pi/devjava/GUI/addGard.fxml"), "Dashboard", null);
           wv.getScene().getWindow().hide();
        
    }
}
        
    
