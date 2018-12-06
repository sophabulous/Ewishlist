package com.EbucketList.email;

import java.net.URL;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.api.TrackingApiController;

import java.util.Random;

import javax.activation.*;

public class Email {

    private static final Logger log = LoggerFactory.getLogger(TrackingApiController.class);
	
	private static URL backgroundUrl = Email.class.getResource("/background.jpg");
	
	/**
	 * Returns true if email is formated correctly (not if it exists)
	 */
	public static boolean validateEmail(String email){
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			return false;
		}
		return true;
	}

	/**
	 * Returns text with each character set to a random colour, also changes '\n' to <br>
	 */
	private static String colourText(String text){
		String colourMsg = "";
		String[] colours = {"red", "orange", "yellow", "lime", "cyan", "blue", "magenta"};
		Random rand = new Random();
		int index;
		
		for (int i = 0; i < text.length(); i++) {
			index = rand.nextInt(colours.length);
			if (text.charAt(i) == '\n')
				colourMsg += "<br>";
			else
				colourMsg += "<font color=\"" + colours[index] + "\">" + text.charAt(i) + "</font>";
		}
		return colourMsg;
	}

	/**
	 * Sends an email to address 'to' from email 'FROM'
	 */
	public static void sendEmail(String from, String pword, String to, String subject, String msg){    

		//Get properties object    
		Properties props = new Properties();    
		props.put("mail.smtp.host", "smtp.gmail.com");   
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");    
		props.put("mail.smtp.port", "587"); 

		//get Session 
		Session session = Session.getInstance(props,    
				new javax.mail.Authenticator() {    
			protected PasswordAuthentication getPasswordAuthentication() {    
				return new PasswordAuthentication(from, pword);  
			}    
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);

			// create multipart to add multiple BodyParts to msg
			MimeMultipart multipart = new MimeMultipart();
			BodyPart msgBodyPart = new MimeBodyPart();

			// style
			String style = "style=\""
					+ "background-image:url(cid:image);"
					+ "border-top: 5px double red ;"
					+ "border-right: 5px dashed magenta;"
					+ "border-bottom: 15px dotted lime;"
					+ "border-left: 10px solid cyan;"
					+ "font-family: Comic Sans MS, cursive, sans-serif;"
					+ "font-size: 300%;"
					+ "font-weight: bold;"
					+ "letter-spacing: 10px;"
					+ "\"";

			// colour msg
			msg = colourText(msg);

			// main msg html
			String msgHTML = "<body " + style + ">"
					+ msg
					+ "</body>";
			
			// add html to multipart
			msgBodyPart.setContent(msgHTML, "text/html");
			multipart.addBodyPart(msgBodyPart);

			// add img for background to multipart
			DataSource imgFile = new FileDataSource(backgroundUrl.getFile());
			msgBodyPart = new MimeBodyPart();
			msgBodyPart.setDataHandler(new DataHandler(imgFile));
			msgBodyPart.setHeader("Content-ID", "<image>");
			multipart.addBodyPart(msgBodyPart);

			message.setContent(multipart);

			Transport.send(message);
			log.debug("Sent message successfully to " + to);
		
		} catch (MessagingException mex) {
			log.error("Failed to send message to " + to);
			mex.printStackTrace();
		}
	}
}
