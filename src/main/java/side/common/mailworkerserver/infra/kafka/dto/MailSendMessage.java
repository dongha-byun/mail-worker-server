package side.common.mailworkerserver.infra.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MailSendMessage {
    private String email;
    private String title;
    private String content;
}
