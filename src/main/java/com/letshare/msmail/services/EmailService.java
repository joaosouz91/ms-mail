package com.letshare.msmail.services;

import com.letshare.msmail.domain.dtos.EmailDto;
import com.letshare.msmail.domain.entities.Email;
import com.letshare.msmail.domain.enums.Status;
import com.letshare.msmail.domain.mapper.EmailMapper;
import com.letshare.msmail.repositories.EmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Joao Victor
 * @version 1.0
 * @since 06/08/2022
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;
    private final EmailMapper mapper;

    public void register(EmailDto dto) {
        log.info("Registering email to {}", dto.getTo());

        var email = mapper.toEntity(dto);
        email.setStatus(Status.PENDING);
        emailRepository.save(email);

        log.info("Email successfully registered to {}", dto.getTo());
    }

    public void send(List<Email> emails) {

        emails.forEach(email -> {

            log.info("Sending email to {}", email.getEmailTo());
            email.setSentDate(LocalDateTime.now());

            var message = mapper.toMessage(email);

            try {
                mailSender.send(message);
                email.setStatus(Status.SENT);
                log.info("Email successfully sent to {}", email.getEmailTo());
            } catch (Exception e) {
                email.setStatus(Status.FAILED);
                log.info("Failed to send email to {}", email.getEmailTo());
            } finally {
                emailRepository.save(email);
            }
        });
    }

    // Must be implemented recursively to fetch all pending emails till the end of the list.
    public List<Email> findAllPending(Pageable pageable) {
        return emailRepository.findByStatus(Status.PENDING, pageable);
    }

}
