/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties; 
/**
 *
 * @author ASUS
 */
public class SebMail { 
    public static void sendMail(String emailAddress, String emailSubject, String emailMessage  ){
        String to = emailAddress;
        String subject = emailSubject;
        String msg =emailMessage;
        final String from ="nourtrabelsi833@gmail.com";
        final  String password ="azerty987";
        Properties props = new Properties();
		props.setProperty("mail.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field
            message.setSubject(subject);
            // Now set the actual message
            message.setText(msg);
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully...."); 
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}