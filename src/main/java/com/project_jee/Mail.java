package com.project_jee;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class Mail {
	
	public void envoyer(String to, String obj, String contenu) {
		
		Properties prop = new Properties();
	    prop.put("mail.smtp.host", "smtp.gmail.com");
	    prop.put("mail.smtp.port", "587");
	    prop.put("mail.smtp.auth", "true");
	    prop.put("mail.smtp.starttls.enable", "true"); //TLS
	       
	    Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("monannonce587@gmail.com", "monannonce12");
            }
        });


        try {
        	
        	System.out.print("\nLe mail : " + to + " " + obj + " " + contenu);

        	Message message = new MimeMessage(session);
        	message.setFrom(new InternetAddress("monannonce587@gmail.com", "Mon Annonce"));
        	message.setRecipient( Message.RecipientType.TO, new InternetAddress(to));
        	message.setSubject(obj);
        	message.setText(contenu);

        	Transport.send(message);

        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        
	}
}