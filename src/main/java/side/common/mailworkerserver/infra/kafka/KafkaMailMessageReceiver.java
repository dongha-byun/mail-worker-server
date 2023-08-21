package side.common.mailworkerserver.infra.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import side.common.mailworkerserver.domain.MailMessageReceiver;
import side.common.mailworkerserver.domain.MailSendWorker;
import side.common.mailworkerserver.infra.kafka.dto.MailSendMessage;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaMailMessageReceiver implements MailMessageReceiver {

    private final ObjectMapper objectMapper;
    private final MailSendWorker mailSendWorker;

    @Override
    @KafkaListener(topics = "notify_mail_topic")
    public void receive(String message) {
        log.info("receive message => {}", message);
        try {
            MailSendMessage mailSendMessage = objectMapper.readValue(message, MailSendMessage.class);
            mailSendWorker.sendMail(
                    mailSendMessage.getEmail(), mailSendMessage.getTitle(), mailSendMessage.getContent()
            );

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
