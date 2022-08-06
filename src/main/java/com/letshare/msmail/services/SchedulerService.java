package com.letshare.msmail.services;

import com.letshare.msmail.domain.entities.Scheduler;
import com.letshare.msmail.repositories.SchedulerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicBoolean;

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

    @Scheduled(fixedRate = 5000)
    public void run() {

        notifyIsRunning();

        var pageable = PageRequest.of(0, 20);
        var emails = emailService.findAllPending(pageable);
        emailService.send(emails);

        // simulating a delay of 10 seconds to finish the whole process
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
        schedulerRepository.save(Scheduler.builder()
                .key("email_scheduler")
                .isRunning(false)
                .build());
    }

    // not going to be used
    @Deprecated
    private boolean isRunning() {
        var isRunning = new AtomicBoolean(false);
        schedulerRepository.findByKey("scheduler").ifPresent(scheduler -> isRunning.set(scheduler.isRunning()));
        return isRunning.get();
    }


}
