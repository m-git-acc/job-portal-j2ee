/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.backend.etc;


import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

/**
 *
 * @author Mohit
 */
public class SendConfirmationMail
{	
    public static void sendConfirmationMail(String to_email, String subject, String message)
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        final String username = "USERNAME OF MAIL ID";
        final String password = "PASSWORD";


        //session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message2 = new MimeMessage(session);
            message2.setRecipient(Message.RecipientType.TO, new InternetAddress(to_email));
            message2.setFrom(new InternetAddress("EMAIL ID OF WEBSITE OWNER(FROM_EMAIL)"));
            message2.setSubject(subject);
            message2.setText(message);
            Transport.send(message2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
