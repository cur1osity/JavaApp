package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTest {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Once a day email";

    @Test
    public void shouldCreateProperEmail() {

        //Given
        given(taskRepository.count()).willReturn(2L);
        given(adminConfig.getAdminMail()).willReturn("admin@example.com");
        given(adminConfig.getSecondEmail()).willReturn("secondMail@example.com");

        Mail mail = new Mail("admin@example.com", "secondMail@example.com", SUBJECT, "Currently in database you got: 2 tasks" );

        //When & Then
        assertThat(emailScheduler.createMail()).isEqualToComparingFieldByField(mail);
    }

    @Test
    public void shouldCreateProperEmailWith1Task() {

        //Given
        given(taskRepository.count()).willReturn(1L);
        given(adminConfig.getAdminMail()).willReturn("admin@example.com");
        given(adminConfig.getSecondEmail()).willReturn("secondMail@example.com");

        Mail mail = new Mail("admin@example.com", "secondMail@example.com", SUBJECT, "Currently in database you got: 1 task" );

        //When & Then
        assertThat(emailScheduler.createMail()).isEqualToComparingFieldByField(mail);
    }
}