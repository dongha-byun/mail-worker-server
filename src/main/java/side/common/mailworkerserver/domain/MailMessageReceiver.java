package side.common.mailworkerserver.domain;

public interface MailMessageReceiver {
    void receive(String message);
}
