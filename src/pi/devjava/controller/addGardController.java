/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;
import org.json.JSONException;
import org.json.JSONObject;
import static pi.devjava.PIDevJava.bdd;
import static pi.devjava.controller.LoginController.loadWindow;
import static pi.devjava.controller.MapController.adrGard;
import static pi.devjava.controller.addGardController.ad;
import static pi.devjava.controller.interfaceResponsableController.ident;
import pi.devjava.entities.Garderie;
import pi.devjava.entities.Utilisateur;
import pi.devjava.services.GarderieService;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class addGardController implements Initializable {
    public String URL ;
        boolean verificationImage=false;
        
 @FXML
    private Label id;

    @FXML
    private AnchorPane frame;
    @FXML
    private AnchorPane ap;
    @FXML
    private Label inscrirLabel;
    @FXML
    private JFXTextField numg;
    @FXML
    private JFXTextField nom;
     @FXML
    private JFXTextField numg1;
    @FXML
    private JFXTextField nom1;
    @FXML
    private JFXButton addGardButton;
    @FXML
    private ImageView close;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField tel1;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextArea aaa;
    @FXML
    private JFXTextArea aaa1;
    @FXML
    private Label inscrirLabel1;
    @FXML
    private ImageView view1;
    @FXML
    private ImageView view2;
@FXML
    private ImageView view3;
    FileInputStream fis = null;
    File f=null;
    List<String> lstFile;
     @FXML
    private Label labelImage;
      @FXML
    private WebView wv;
    @FXML
    private JFXTextField adr;
    @FXML
    private Label lc;
    static String adrGard;
     static String ad;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        WebEngine webEngine = wv.getEngine();


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
                        aaa.setText(myresponse.getString("display_name"));
                        adresse.setText(myresponse.getString("display_name"));
                      System.out.println(myresponse.getString("display_name"));
                      ad=adr.getText();
                      
                      
                      
                  } catch (MalformedURLException ex) {
        Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
    } catch (JSONException ex) {
        Logger.getLogger(MapController.class.getName()).log(Level.SEVERE, null, ex);
    }
               
               });
        
        lstFile = new ArrayList<>();
        lstFile.add("*.png");
        lstFile.add("*.jpg");
                        ap.setVisible(false);
                       aaa1.setText(ad);
                        adresse.setText(ad);
                        adresse.setVisible(false);
                        System.out.println(Utilisateur.uName);
                       //id.setText(ident.toString());
                        if (bdd.getInt("SELECT * FROM garderie WHERE resp_id ="+ident+";",1)==-1)
                        {
                          aaa.setVisible(true);
                          nom.setVisible(true);
                          numg.setVisible(true);
                          tel.setVisible(true);
                          addGardButton.setVisible(true);
                          //id.setText("mouch mawjoud");
                          aaa1.setVisible(false);
                          nom1.setVisible(false);
                          numg1.setVisible(false);
                          tel1.setVisible(false);
                          
                        }
                        else 
                        {
                            aaa1.setVisible(true);
                          nom1.setVisible(true);
                          numg1.setVisible(true);
                          tel1.setVisible(true);
                          view2.setVisible(true);
                          
                                aaa.setVisible(false);
                          nom.setVisible(false);
                          numg.setVisible(false);
                          tel.setVisible(false);
                          view1.setVisible(false);
                          view2.setVisible(false);
                          adresse.setVisible(false);
                          inscrirLabel.setText("Garderie");
                          addGardButton.setVisible(false);
                          aaa1.setText(bdd.getString("SELECT adresse FROM garderie WHERE resp_id ="+ident+";",1));
                          nom1.setText(bdd.getString("SELECT nom FROM garderie WHERE resp_id ="+ident+";",1));
                          numg1.setText(bdd.getString("SELECT numGard FROM garderie WHERE resp_id ="+ident+";",1));
                          tel1.setText(bdd.getString("SELECT telephone FROM garderie WHERE resp_id ="+ident+";",1));
        String name=(bdd.getString("SELECT image FROM garderie WHERE resp_id ="+ident+";",1));
        System.out.println(name);
        if (name=="")
        {
        view3.setVisible(true);
        }
        else{
               
        FileInputStream inputstream; 
        try {
            inputstream = new FileInputStream("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+name);
             Image img;
            ImageView image1=new ImageView();
            img =new Image(inputstream);
            
            view3.setImage(img);
            view3.setFitHeight(250);
            view3.setFitWidth(250);
                          
                        }       catch (FileNotFoundException ex) {
                                    Logger.getLogger(addGardController.class.getName()).log(Level.SEVERE, null, ex);
                                }}}}

        
        // TODO
    
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


    @FXML
    private void addGard(MouseEvent event) throws SQLException {
        
         if(numg.getText().equals("")){
            {
                Alert a1=new Alert(Alert.AlertType.ERROR);
                a1.setTitle(null);
                a1.setHeaderText("num gard not null");
                a1.show();
                
                
            }
        }else if(nom.getText().equals("")){
            {
                Alert a1=new Alert(Alert.AlertType.ERROR);
                a1.setTitle(null);
                a1.setHeaderText("username not null");
                a1.show();
                
                
            }
        }else if(tel.getText().equals("")){
            {
                Alert a1=new Alert(Alert.AlertType.ERROR);
                a1.setTitle(null);
                a1.setHeaderText("number not null");
                a1.show();
                
                
            }
        }else if(adresse.getText().equals("")){
            {
                Alert a1=new Alert(Alert.AlertType.ERROR);
                a1.setTitle(null);
                a1.setHeaderText("addres not null");
                a1.show();
                
               
            }
        }else if(f==null){
            {
                Alert a1=new Alert(Alert.AlertType.ERROR);
                a1.setTitle(null);
                a1.setHeaderText("image not null");
                a1.show();
                
                
            }}
        else {
            saveToFile( new Image(f.toURI().toString()),f.getName());
            Garderie g = new Garderie(numg.getText(),nom.getText(),adresse.getText(),tel.getText(),f.getName(),ident);
            //  Garderie g = new Garderie          
                        GarderieService gard = new GarderieService();
                        gard.addGarderie(g);
                        
                        /*
                         aaa1.setVisible(true);
                          nom1.setVisible(true);
                          numg1.setVisible(true);
                          tel1.setVisible(true);
                          aaa1.setText(bdd.getString("SELECT adresse FROM garderie WHERE resp_id ="+ident+";",1));
                          nom1.setText(bdd.getString("SELECT nom FROM garderie WHERE resp_id ="+ident+";",1));
                          numg1.setText(bdd.getString("SELECT numGard FROM garderie WHERE resp_id ="+ident+";",1));
                          tel1.setText(bdd.getString("SELECT telephone FROM garderie WHERE resp_id ="+ident+";",1));
                          addGardButton.setVisible(false);
                          //id.setText("mouch mawjoud");
                          aaa.setVisible(false);
                          nom.setVisible(false);
                          numg.setVisible(false);
                          tel.setVisible(false);*/
        }
        System.out.println(lstFile);
    }
    public static void saveToFile(Image image,String name) {
        
         File outputFile = new File("C:\\Users\\youssef\\Documents\\NetBeansProjects\\PI-DevJava\\src\\pi\\devjava\\images\\"+name);
                  File outputw = new File("C:\\wamp64\\www\\PiDevWebb\\web\\uploads\\"+name);

         BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    try {
        ImageIO.write(bImage, "png", outputFile);
        ImageIO.write(bImage, "png", outputw);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
            
    

    @FXML
    private void closeApplication(MouseEvent event) {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Vous allez quitter l'inerface garderie");
        alert.setHeaderText("Vous allez quitter l'inerface garderie");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
             aaa.getScene().getWindow().hide();
        } else {
            alert.close();
}
    }

    @FXML
    private void ChargeImage(MouseEvent event) throws MalformedURLException, FileNotFoundException{
         FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("word file", lstFile));
        f = fc.showOpenDialog(null);
        
        
        if (f!=null)
        {
            //lab.setText(f.getAbsolutePath());
            Image image = new Image(f.toURI().toString());
            view2.setImage(image) ;
            view2.setVisible(true);
            view1.setVisible(false);
            labelImage.setText("Cliquez sur l'image pour la changer!");
            URL=f.getPath();
            if(URL!="")
            {
            verificationImage=true;
            }
            else 
                verificationImage=false;
        }
        
    }

    @FXML
    private void ChangeImage(MouseEvent event) throws MalformedURLException, FileNotFoundException{
        
           FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("word file", lstFile));
       f = fc.showOpenDialog(null);
        
        if (f!=null)
        {
            //lab.setText(f.getAbsolutePath());
            Image image = new Image(f.toURI().toString());
            view2.setImage(image) ;
            view2.setVisible(true);
            view1.setVisible(false);
            labelImage.setText("Cliquez sur l'image pour la changer!");
            URL=f.getPath();
            System.out.println(URL.toString());
            System.out.println(fc);
            if (URL != "")
            {
            verificationImage=true;
            }
            else 
                verificationImage=false;
        }
        
    }
    @FXML
    private void mapAdr(MouseEvent event){
       /* loadWindow(getClass().getResource("/pi/devjava/GUI/map.fxml"), "Dashboard", null);
                adresse.getScene().getWindow().hide();
               aaa.setVisible(true);
*/
       ap.setVisible(true);
           aaa.setVisible(false);
                          nom.setVisible(false);
                          numg.setVisible(false);
                          tel.setVisible(false);
                          view1.setVisible(false);
                          view2.setVisible(false);
                              aaa1.setVisible(false);
                          nom1.setVisible(false);
                          numg1.setVisible(false);
                          tel1.setVisible(false);
                          addGardButton.setVisible(false);
                          inscrirLabel.setVisible(false);
                          
                          
       
       
                
       //adresse.setText(MapController.adrGard);  
       // System.out.println(MapController.adrGard);
    }
        

    //  System.out.println(URL.toString());
    
    
    
      
       
       
       
      //  webEngine.loadContent("");
    
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
      
    
    public void retAj(MouseEvent event){
        //addGardController.ad=adrGard;
           //loadWindow(getClass().getResource("/pi/devjava/GUI/addGard.fxml"), "Dashboard", null);
          // wv.getScene().getWindow().hide();
          ap.setVisible(false);
          aaa.setVisible(true);
                          nom.setVisible(true);
                          numg.setVisible(true);
                          tel.setVisible(true);
                          addGardButton.setVisible(true);
                          inscrirLabel.setVisible(true);
                          view1.setVisible(false);
                          view2.setVisible(true);
       frame.setVisible(true);
    }

}
    

