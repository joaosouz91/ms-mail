package com.letshare.msmail.resources;

import com.letshare.msmail.domain.dtos.EmailDto;
import com.letshare.msmail.services.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Joao Victor
 * @version 1.0
 * @since 06/08/2022
 */

@RequiredArgsConstructor
@RestController
public class EmailResource {

    private final EmailService emailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmailDto> registerEmail(@RequestBody @Valid EmailDto dto) {
        emailService.register(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

}
