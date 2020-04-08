package me.liheng;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailWithVelocity {

    public static void main(String[] args) {
        String to = "liheng6567@me.com";
        String from = "liheng6567@gmail.com";

        //To use Gmail's SMTP server,need the following settings for outgoing emails
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");

        //Get the Session object
        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, "16 digit app password");
                    }
                });

        try {
            //Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);

            //Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            //Set Subject: header field
            message.setSubject("This is the Subject Line!");

            //Now set the actual message
            message.setText("This is actual message");

            //Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
