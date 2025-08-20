package quantiLearn.notification_service.service.serviceImpl;

import com.quantilearn.eventmodels.ProfileCreatedDto;
import io.mailtrap.client.MailtrapClient;
import io.mailtrap.config.MailtrapConfig;
import io.mailtrap.factory.MailtrapClientFactory;
import io.mailtrap.model.request.emails.Address;
import io.mailtrap.model.request.emails.MailtrapMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import quantiLearn.notification_service.dto.EmailContentDto;
import quantiLearn.notification_service.service.EmailService;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${email.token}")
    private String TOKEN;

    @Value("${email.sender}")
    private String SENDER_EMAIL;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    //private static final String REPLY_TO_EMAIL = "reply_to@domain.com";
    //private static final String RECIPIENT_EMAIL = "recipient@domain.com";

    @Override
    public void sentEmail(EmailContentDto contentDto) {
        triggerEmail(contentDto);
    }

    @Override
    public void sentProfileCreatedEmail(ProfileCreatedDto profileCreatedDto) {
        EmailContentDto emailContentDto = new EmailContentDto();
        emailContentDto.setRecipient_email(profileCreatedDto.getEmail());

        Context context = new Context();
        context.setVariable("username", profileCreatedDto.getFirstName() + " " + profileCreatedDto.getLastName());

        String html=springTemplateEngine.process("profile-created",context);

        emailContentDto.setBody(html);

        emailContentDto.setSubject("Profile Created Successfully!");

        triggerEmail(emailContentDto);
    }

    private void triggerEmail(EmailContentDto contentDto){
        // create config
        final MailtrapConfig config = new MailtrapConfig.Builder().token(TOKEN).build();

        // create client
        final MailtrapClient client = MailtrapClientFactory.createMailtrapClient(config);

        // create mail object
        final MailtrapMail mail = MailtrapMail.builder()
                .from(new Address(SENDER_EMAIL))
                .to(Stream.of(new Address(contentDto.getRecipient_email())).collect(Collectors.toList()))
                //.replyTo(new Address(REPLY_TO_EMAIL, "Vincent Vega"))
                .subject(contentDto.getSubject())
                .html(contentDto.getBody())
                .build();

        // Send email using Mailtrap Sending API
        try {
            System.out.println(client.send(mail));
        } catch (Exception e) {
            System.out.println("Caught exception : " + e);
        }

//        // Or send email using Mailtrap Testing API
//        try {
//            long inboxId = 1000001L;
//
//            // Either instantiate a new client
//            MailtrapClient sandboxClient = MailtrapClientFactory.createMailtrapClient(
//                    new MailtrapConfig.Builder()
//                            .sandbox(true)
//                            .inboxId(inboxId)
//                            .token(TOKEN)
//                            .build());
//
//            System.out.println(sandboxClient.send(mail));
//
//            // Or reuse already created client
//            client.switchToEmailTestingApi(inboxId);
//
//            System.out.println(client.send(mail));
//
//            // Or use Testing API directly
//            System.out.println(client.testingApi().emails().send(mail, inboxId));
//        } catch (Exception e) {
//            System.out.println("Caught exception : " + e);
//        }
    }
}
