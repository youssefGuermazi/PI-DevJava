/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.devjava.services;

/**
 *
 * @author chabene
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class mailer {


 
   

     private static final String HOST = "smtp.gmail.com";
    private static final String FROM ="nourtrabelsi833gmail.com";
    private static final String LOGIN = "nourtrabelsi833@gmail.com";
    private static final String PASS = "azert987";

    public static void sendMail(String recipient, String title, String content) {
        Thread mailThread = new Thread(() -> {
            Transport transport = null;
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", HOST);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

            Session session = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(LOGIN, PASS);
                }
            });
            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(FROM));

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

                message.setSubject(title);

                message.setText(content);

                System.out.println("Tentative de connexion");

                transport = session.getTransport("smtp");
                System.out.println("Tentative de connexion");
                transport.connect(HOST, LOGIN, PASS);

                System.out.println("Envoi de mail...");

                transport.sendMessage(message, message.getAllRecipients());

                System.out.println("Mail enovyé avecc succés.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        mailThread.start();
    }}
    

