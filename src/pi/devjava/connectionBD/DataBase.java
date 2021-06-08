/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.connectionBD;

/**
 *
 * @author youssef
 */
/** Nos imports */
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
public class DataBase {
    private String host;
	private String name;
	private String user;
	private String pass;
	private String url;
	private Connection conn;
	
	public DataBase(String h, String n, String u, String p){
		this.host = h;
		this.name = n;
		this.user = u;
		this.pass = p;
		this.url = "jdbc:mysql://"+this.host+"/"+this.name+"?useUnicode=yes&characterEncoding=UTF-8";
	}

	/** Méthode de Connection */
	public void connection(){
		try {
       Class.forName("com.mysql.jdbc.Driver"); 
       System.out.println("driver chargeé");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
       
        try {
             this.conn = DriverManager.getConnection(this.url, this.user, this.pass);
             System.out.println("Je me suis connecte a la BDD!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Rt. ma vie est nulle, j'ai pas reussi a me connecter.");
        }
    }

	/** Méthode de Vérification de Connexion */
    public boolean connected() {
        return this.conn != null;
    }

	/** Méthode de Connexion si Déconnecté */
	private void connectIfNot(){
   	 if (!this.connected()) this.connection();
	}

	/** Méthode de Deconnexion */
	public void disconnection(){
	if(this.connected()) this.conn = null;
	}

	/** Méthode de Lecture de String */
   public String getString(String request, int ci){
		this.connectIfNot();
    	try {
            Statement state = this.conn.createStatement();
            ResultSet result = state.executeQuery(request);
                    try {
                        while(result.next()){
                            return result.getString(ci);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
    	 } catch (SQLException e) {
             e.printStackTrace();
         }
    	return "NULL";
    }

	/** Méthode de Récupération de Integer */
    public int getInt(String request, int ci){
		this.connectIfNot();
    	try {
            Statement state = this.conn.createStatement();
            ResultSet result = state.executeQuery(request);
                    try {
                        while(result.next()){
                            return result.getInt(ci);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
    	 } catch (SQLException e) {
             e.printStackTrace();
         }
    	return -1;
    }
    

	/** Méthode d'envoie de requête d'écriture */
    public void sendRequest(String request){
        this.connectIfNot();
        
        try{
        	Statement state = this.conn.createStatement();
        	state.executeUpdate(request);
        	state.close();
        	System.out.println("Oee! ca marche " + request);
        }
        catch(SQLException e){
            e.printStackTrace();
        	System.out.println("Noo! ce marche pas " + request);
        }
    }

	/** Méthode de Vérification de présence de donnée */
    public boolean isInBdd(String request) {
        try {
            Statement state = this.conn.createStatement();
            ResultSet result = state.executeQuery(request);
            if (result.next()) {
                return true;
            }
            return false;
        } 
        catch (Exception e) {
            return false;
        }
    }
        public Boolean getBool(String request, int ci){
		this.connectIfNot();
    	try {
            Statement state = this.conn.createStatement();
            ResultSet result = state.executeQuery(request);
                    try {
                        while(result.next()){
                            if(result.getInt(ci)==0){
                                return false ;
                            }
                            else return true;
                            
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
    	 } catch (SQLException e) {
             e.printStackTrace();
         }
    	return false;
    }
        public Date getDate(String request , int ci){
            this.connectIfNot();
            Date d=null;
            try {
            Statement state = this.conn.createStatement();
            ResultSet result = state.executeQuery(request);
                    try {
                        while(result.next()){
                            d = result.getDate(ci);
                            
                            
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
    	 } catch (SQLException e) {
             e.printStackTrace();
         }
             return d;
        }
        private static DataBase instance;
    private Connection connexion;
    private String url1 = "JDBC:mysql://localhost/learn and play";
    private String user1 = "root";
    private String password = "";
    private static Statement stmt = null;

    private DataBase() {
        try {
            connexion = DriverManager.getConnection(url1, user1, password);
        } catch (SQLException ex) {
            System.out.println("Probleme de connexion");
        }
    }

    public static DataBase getInstance() {
        if (DataBase.instance == null) {
            DataBase.instance = new DataBase();
        }
        return DataBase.instance;
    }

    public Connection getConnexion() {
        return this.connexion;
    }
     public Connection getConnection() {
        return this.connexion;
    }
       
}
