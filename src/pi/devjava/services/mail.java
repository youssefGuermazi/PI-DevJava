/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mbm info
 */
public class mail {
     public void mail (String MSG) 
    {
        String host="nourtrabelsi833@gmail.com";
                    final String user="nourtrabelsi833@gmail.com";//change accordingly
                    final String password="azerty987";//change accordingly
                    
                    //tring to="nourtrabelsi833@gmail.com ";//change accordingly
                    String to="beya.hariz@esprit.tn";//change accordingly
                    
                    //Get the session object
                    Properties props = new Properties();
                    props.put("mail.smtp.ssl.trust", "*");
                    
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.host", "smtp.gmail.com");
                    props.put("mail.smtp.starttls.enable", "true");
                    Session session = Session.getDefaultInstance(props,
                            new javax.mail.Authenticator() {
                                protected PasswordAuthentication getPasswordAuthentication() {
                                                                        return new PasswordAuthentication(user,password);

                                }
                                });
                            
                    
                    //Compose the message
                  
                        try {  
     MimeMessage message = new MimeMessage(session);  
     message.setFrom(new InternetAddress(user));  
     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
     message.setSubject("AFFECTATION");  
              message.setText(MSG  ); 

      
            System.out.println("message sent successfully...");  

    //send the message  
     Transport.send(message); 
       
     } catch (MessagingException e)
      {e.printStackTrace();}  
                
    }
    
}
