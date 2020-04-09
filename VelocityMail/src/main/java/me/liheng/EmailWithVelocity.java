package me.liheng;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
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
                        return new PasswordAuthentication(from, "16 digit");
                    }
                });

        try {

            //Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);
            //Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //Set Subject: header field
            message.setSubject("This is the Subject Line!");

            emailWithAttachment(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


    //simple email
    private static void emailWithText(MimeMessage message) throws MessagingException {
        //set the actual message
        message.setText("This is actual message");
        //send message
        Transport.send(message);
    }


    //email with attachment
    private static void emailWithAttachment(MimeMessage message) throws MessagingException {
        //create MimeBodyPart object and set message text
        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setText("This is the message body!\n");

        //create new MimeBodyPart object and set DataHandler object to MimeBodyPart
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
        String filename = "src/main/resources/attachments/a.txt";
        DataSource source = new FileDataSource(filename);
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName("a.txt");

        //create Multipart object and add MimeBodyPart objects to this object
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart1);
        multipart.addBodyPart(messageBodyPart2);

        //set the multiplart object to the message object
        message.setContent(multipart);

        //send message
        Transport.send(message);
    }
}
