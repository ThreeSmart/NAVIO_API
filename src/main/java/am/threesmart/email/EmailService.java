package am.threesmart.email;

import am.threesmart.enums.EmailType;
import am.threesmart.exceptions.NoSuchEmailTypeException;
import am.threesmart.exceptions.UserNotFountException;
import am.threesmart.models.dto.SendEmail;
import am.threesmart.models.dto.User;
import am.threesmart.services.TokenService;
import am.threesmart.services.UserService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    private final TokenService tokenService;

    private final UserService userService;

    private final static String SENDER_EMAIL_ADDRESS = "noreply@threesmart.am";

    public EmailService(final JavaMailSender javaMailSender, final TokenService tokenService, final UserService userService) {
        this.javaMailSender = javaMailSender;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    public void send(final SendEmail sendEmail) {
        switch (sendEmail.getEmailType()) {
            case RESET_PASSWORD: {
                final User userByEmail = userService.getUserByEmail(sendEmail.getEmail());
                if (userByEmail == null) throw new UserNotFountException();

                final OverrideRules overrideRules = new OverrideRules();
                overrideRules
                        .addRule("%%host%%", "threesmart.am/reset_password")
                        .addRule("%%token%%", tokenService.generateOneTimeToken(userByEmail.getId()));
                send(sendEmail, overrideRules);
                break;
            }
            default: {
                throw new NoSuchEmailTypeException();
            }
        }
    }

    private void send(final SendEmail sendEmail, final OverrideRules overrideRules) {
        final String to = sendEmail.getEmail();
        final EmailType emailType = sendEmail.getEmailType();
        try {
            final MimeMessage message = javaMailSender.createMimeMessage();
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            String contentByType = EmailHtmlContents.getContentByType(emailType).replace("\n", "");
            for (final OverrideRules.SinglePair overrideRule : overrideRules) {
                contentByType = contentByType.replace(overrideRule.getKey(), overrideRule.getValue());
            }
            message.setContent(contentByType, "text/html");
            message.setSubject(EmailHtmlContents.getSubjectByType(emailType));
            message.setFrom(SENDER_EMAIL_ADDRESS);

            System.out.printf( "The email to: %s was sent with type: %s\n", sendEmail, emailType);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
