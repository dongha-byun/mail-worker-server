package side.common.mailworkerserver.domain;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MailSendWorker {

    private final JavaMailSender mailSender;

    public boolean sendMail(String email, String title, String message) {
        MimeMessagePreparator preparator = mimeMessage -> {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setSubject(title);
            mimeMessage.setText(message);
        };

        try{
            mailSender.send(preparator);
        }catch (MailException ex) {
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
