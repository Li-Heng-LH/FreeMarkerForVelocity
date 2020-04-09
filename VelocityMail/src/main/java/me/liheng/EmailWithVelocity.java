package me.liheng;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.util.*;

public class EmailWithVelocity {

    final static String TO_ADDRESS = "liheng6567@me.com";
    final static String FROM_ADDRESS = "liheng6567@gmail.com";
    static String password = "";

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();
        setProperties(properties);

        //Get the Session object
        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM_ADDRESS, password);
                    }
                });

        String messageContent = generateContentFromFreeMarker();

        try {

            //Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);
            //Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(TO_ADDRESS));
            //Set Subject: header field
            message.setSubject("This is the Subject Line!");

            emailWithHtmlAndAttachment(message, messageContent);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private static void emailWithHtmlAndAttachment(MimeMessage message, String messageContent) throws MessagingException {
        //create MimeBodyPart object and set message text
        BodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.setContent(messageContent,"text/html");

        //create new MimeBodyPart object and set DataHandler object TO_ADDRESS MimeBodyPart
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
        String filename = "src/main/resources/attachments/a.txt";
        DataSource source = new FileDataSource(filename);
        messageBodyPart2.setDataHandler(new DataHandler(source));
        messageBodyPart2.setFileName("a.txt");

        //create Multipart object and add MimeBodyPart objects TO_ADDRESS this object
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart1);
        multipart.addBodyPart(messageBodyPart2);

        //set the multiplart object TO_ADDRESS the message object
        message.setContent(multipart);

        //send message
        Transport.send(message);
    }

    private static void setProperties(Properties properties) throws Exception {
        FileInputStream in = new FileInputStream("src/main/resources/config.properties");
        properties.load(in);
        in.close();
        password = properties.getProperty("password");

        System.out.println(password);

        //To use Gmail's SMTP server,need the following settings for outgoing emails
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
    }

    private static String generateContentFromVelocity() {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init();
        Template template = velocityEngine.getTemplate("/src/main/resources/vtemplates/emailTemplate.vm");
        VelocityContext context = new VelocityContext();
        context.put("firstName","Heng");
        context.put("lastName","Li");
        context.put("signature","LH");
        context.put("location","SG");

        List<Product> products = new ArrayList<>();
        products.add(new Product("iPad Pro", 1349.00,"https://www.apple.com/sg/ipad-pro/"));
        products.add(new Product("MacBook Pro", 3499.00,"https://www.apple.com/sg/macbook-pro-16/"));
        products.add(new Product("Apple Watch", 599.00, "https://www.apple.com/sg/apple-watch-series-5/"));
        context.put("products", products);

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

    private static String generateContentFromFreeMarker() throws IOException, TemplateException {
        /* Create and adjust the configuration singleton */
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        cfg.setDirectoryForTemplateLoading(new File("/Users/liheng/OneDrive/FreeMarkerForVelocity/VelocityMail/src/main/resources/ftemplates"));
        // Recommended settings for new projects:
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

        Map root = new HashMap();
        root.put("firstName","Heng");
        root.put("lastName","Li");
        root.put("signature","LH");
        root.put("location","SG");
        List<Product> products = new ArrayList<>();
        products.add(new Product("iPad Pro", 1349.00,"https://www.apple.com/sg/ipad-pro/"));
        products.add(new Product("MacBook Pro", 3499.00,"https://www.apple.com/sg/macbook-pro-16/"));
        products.add(new Product("Apple Watch", 599.00, "https://www.apple.com/sg/apple-watch-series-5/"));
        root.put("products", products);
        /* Get the template (uses cache internally) */
        freemarker.template.Template temp = cfg.getTemplate("emailTemplate.ftl");

        /* Merge data-model with template */
        Writer out = new StringWriter();
        temp.process(root, out);
        return out.toString();
    }


    //simple email
    private static void emailWithText(MimeMessage message) throws MessagingException {
        //set the actual message
        message.setText("This is actual message");
        //send message
        Transport.send(message);
    }

    //email with HTML
    private static void emailWithHTML(MimeMessage message) throws MessagingException {
        message.setContent("<h1>Hello World H1</h1> <p>This is the message body!</p>","text/html");
        Transport.send(message);
    }

    //email with HTML and attachment
    private static void emailWithHtmlAndAttachment(MimeMessage message) throws MessagingException {
        emailWithHtmlAndAttachment(message, "<h1>Hello World H1</h1> <p>This is the message body!</p>");
    }

}
