/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectControl;

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
 * @author jsarmiento
 */
public class SendMail {
    public void enviar(String email, String arrayContact[],String asunto,String descripcion){
    Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
                                @Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("jsarmiento.proyects@gmail.com","jsarmientoxrl15");
				}
			});

		try {
                        for (String arrayContact1 : arrayContact) {
                            Message message = new MimeMessage(session);
                            message.setFrom(new InternetAddress(arrayContact1));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(arrayContact1));
                            message.setSubject(asunto);
                            message.setText(descripcion);
                            Transport.send(message);
                        }
                        System.out.println("____________________________________________________________________________________________");
			System.out.println("\n---------------------EL PROYECTO HA SIDO COMPARTIDO CON TUS CONTACTOS---------------------");
                        System.out.println("____________________________________________________________________________________________");                        
                        //Regreso a la clase perfil.
                        System.out.println();
                        //Perfil callPerfil=new Perfil();
                       // callPerfil.ShowPerfilUser(email);

		} catch (MessagingException e) {
                        System.out.println("____________________________________________________________________________________________");
			System.out.println("\n----------------------El mensaje no fue enviado, verifica tus contactos---------------------");
                        System.out.println("____________________________________________________________________________________________");
                        System.out.println();
                        //SelectContact callP=new SelectContact();
                        //callP.Contactos(email);
		}
    
    }
}
