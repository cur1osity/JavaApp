package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService3 simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmailwhenCCisNotNull(){
        //  Given
        Mail mail = new Mail("test@test.com", "test2@test.com", "Test", "Test Message");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if(mail.getToCc()!=null){
             mailMessage.setCc(mail.getToCc());
        }

        //  When
        simpleEmailService.send(mail);

        //  Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }

    @Test
    public void shouldSendEmailwhenCCisNull(){
        //  Given
        Mail mail = new Mail("test@test.com", "Test", "Test Message");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if(mail.getToCc()!=null){
            mailMessage.setCc(mail.getToCc());
        }

        //  When
        simpleEmailService.send(mail);

        //  Then
        verify(javaMailSender, times(1)).send(mailMessage);
    }
}