import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService {
    private final String username = "your_email@example.com";
    private final String password = "your_email_password";

    public void sendConfirmationEmail(String toAddress) {
        String subject = "Confirmation Email";
        String body = "Thank you for your registration!";
        sendEmail(toAddress, subject, body);
    }

    public void sendReminderEmail(String toAddress) {
        String subject = "Reminder Email";
        String body = "This is a reminder for your upcoming event!";
        sendEmail(toAddress, subject, body);
    }

    public void sendCancellationEmail(String toAddress) {
        String subject = "Cancellation Email";
        String body = "We're sorry to inform you that your event has been cancelled.";
        sendEmail(toAddress, subject, body);
    }

    private void sendEmail(String toAddress, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.example.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toAddress));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}