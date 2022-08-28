package com.letshare.msmail.services;

import com.letshare.msmail.domain.entities.Scheduler;
import com.letshare.msmail.domain.enums.Status;
import com.letshare.msmail.repositories.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import static com.letshare.msmail.utils.Utils.longStream;

/**
 * @author Joao Victor
 * @version 1.0
 * @since 06/08/2022
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class SchedulerService {

    private final SchedulerRepository schedulerRepository;
    private final EmailService emailService;

    @Value("${page.size}")
    private Integer pageSize;

    @Scheduled(fixedRate = 20000)
    public void run() {

        notifyIsRunning();

        var pendingCount = emailService.countByStatus(Status.PENDING);

        if (pendingCount > 0) {

            var numberOfPages = (pendingCount / pageSize) + (pendingCount % pageSize > 0 ? 1 : 0);

            var pages = longStream(0L, numberOfPages)
                    .stream()
                    .map(i -> PageRequest.of(i.intValue(), pageSize))
                    .collect(Collectors.toSet());

            pages.forEach(page -> {
                var emails = emailService.findAllPending(page);
                emailService.send(emails);
            });
        }

        notifyIsStopped();
    }

    private void notifyIsRunning() {

        var now = LocalDateTime.now();
        var dateTime = now.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        log.info("Sending emails via scheduler {}", dateTime);

        schedulerRepository.save(Scheduler.builder()
                .key("email_scheduler")
                .lastExecutionStart(now)
                .isRunning(true)
                .build());
    }

    private void notifyIsStopped() {
        schedulerRepository.findByKey("email_scheduler").ifPresent(scheduler -> {
            scheduler.setRunning(false);
            schedulerRepository.save(scheduler);
        });
    }
}
