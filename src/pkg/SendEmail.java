package pkg;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class SendEmail {
	
	final String senderEmailID = "chakeycatchat@gmail.com";
	final String senderPassword = "mariaeduardo";
	final String emailSMTPserver = "smtp.gmail.com";
	final String emailServerPort = "465";
	String receiverEmailID = null;
	static String emailSubject = "Teste Mail";
	static String emailBody = ":)";
	
  public SendEmail(String receiverEmailID,String Subject,
	  String Body){
	   
	  
	  this.receiverEmailID= receiverEmailID; 
	  
	  this.emailSubject= Subject;
	
	  this.emailBody=Body;
	  Properties props = new Properties();
	  
	  props.put("mail.smtp.user",senderEmailID);
	  props.put("mail.smtp.host", emailSMTPserver);
	  props.put("mail.smtp.port", emailServerPort);
	  props.put("mail.smtp.starttls.enable", "true");
	  props.put("mail.smtp.auth", "true");
	  props.put("mail.smtp.socketFactory.port", emailServerPort);
	  props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	  props.put("mail.smtp.socketFactory.fallback", "false");
	
      SecurityManager security = System.getSecurityManager();
	  System.out.println("Aguarde uns instantes até a mensagem ser enviada..."); 

	  try{  
		  Authenticator auth = new SMTPAuthenticator();
		  Session session = Session.getInstance(props, auth);
		  MimeMessage msg = new MimeMessage(session);
		  msg.setText(emailBody);
		  msg.setSubject(emailSubject);
		  msg.setFrom(new InternetAddress(senderEmailID));
		  msg.addRecipient(Message.RecipientType.TO,
		  new InternetAddress(receiverEmailID));
		  Transport.send(msg);
		
		  System.out.println("Mensagem enviada com sucesso! "); 
	  }
		  
	  catch (Exception mex){
		  	mex.printStackTrace();  
	     }
	  }
  public class SMTPAuthenticator extends javax.mail.Authenticator
  {
  public PasswordAuthentication getPasswordAuthentication()
  {
  return new PasswordAuthentication(senderEmailID, senderPassword);
  }
  }
     }