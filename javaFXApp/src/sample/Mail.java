package sample;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
/**
 * Created by miczi on 16.01.2017.
 */
public class Mail {

    public void sendMail(String adresat, String movie, String hour, String date){

        final String username = "kinousmieszek@gmail.com";
        final String password = "kinokino!";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

//            String adresat = "";
//            String name = "";
//            String lastname = "";
//            String movie = "";
//            String hour = "";
//            String date = "";
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("kinousmieszek@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(adresat));
            message.setSubject("Reservation in \"Kino Usmieszek\"");
            message.setText("Dear Sirs" +","
                    + "\n\n We want to remind you, about your reservation."
                    + "\n\n Movie: " + movie + " at " + hour
                    + "\n\n Date: " + date
                    + "\n\nWe can't look forward you."
                    + "See you at " + date);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
