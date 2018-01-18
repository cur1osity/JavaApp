package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: Once a day email";

    @Scheduled(cron = "0 0 10 * * *")
//    @Scheduled(fixedDelay = 30000)
    public void sendInformationEmail() {

        long taskCount = taskCount();

        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        adminConfig.getSecondEmail(),
                        SUBJECT,
                        "Currently in database you got: " + taskCount + " " + singularOrPlural(taskCount))
        );
    }


    private String singularOrPlural(Long taskCount) {

        if (taskCount == 1) {
            return "task";
        }
        return "tasks";
    }

    private long taskCount() {
        return taskRepository.count();
    }
}