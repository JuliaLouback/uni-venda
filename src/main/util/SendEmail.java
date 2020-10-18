package main.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import main.entity.Funcionario;

public class SendEmail {

	public void EnviarEmail(Funcionario funcionario) {
		
	    String username = "univenda2@gmail.com";
	    String password = "juliaerikajulio";
	    
	    Properties props = new Properties();
	    
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "465");
	    
	    Session session = Session.getDefaultInstance(props,
	    new javax.mail.Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication(){
	            return new PasswordAuthentication(username, password);
	        }
	    });
	
	    /** Ativa Debug para sessão */
	    session.setDebug(true);
		
	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	       
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(funcionario.getEmail()));
	                 	
	        message.setSubject("Cadastro de Funcionário - Uni Venda");
	        
	        LocalDate data = funcionario.getData_nascimento();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    String dataFormatada = data.format(formatter);
		    
	        StringBuilder sb = new StringBuilder();
	        sb.append(funcionario.getNome() + ", seu cadastro foi efetuado com sucesso").append(System.lineSeparator());
	        sb.append("Para realizar o login utilize o e-mail: "+funcionario.getEmail()+" e senha: " + dataFormatada +".").append(System.lineSeparator());
	   
	        message.setText(sb.toString());
	        
	        Transport.send(message);

	        System.out.println("Feito!!!");

	       } catch (MessagingException e) {
	          throw new RuntimeException(e);
	      }
	}
}
