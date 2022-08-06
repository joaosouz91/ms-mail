package com.letshare.msmail.domain.mapper;

import com.letshare.msmail.domain.dtos.EmailDto;
import com.letshare.msmail.domain.entities.Email;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * @author Joao Victor
 * @version 1.0
 * @since 06/08/2022
 */

@Component
public class EmailMapper {

    public EmailDto toDto(Email email) {
        var dto = new EmailDto();
        BeanUtils.copyProperties(email, dto);
        return dto;
    }

    public Email toEntity(EmailDto dto) {
        var email = new Email();
        BeanUtils.copyProperties(dto, email);
        email.setEmailFrom(dto.getFrom());
        email.setEmailTo(dto.getTo());
        return email;
    }

    public SimpleMailMessage toMessage(Email email) {
        var message = new SimpleMailMessage();
        message.setFrom(email.getEmailFrom());
        message.setTo(email.getEmailTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        return message;
    }

}
