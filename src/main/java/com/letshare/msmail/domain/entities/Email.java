package com.letshare.msmail.domain.entities;

import com.letshare.msmail.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

import static com.letshare.msmail.utils.IdSupplier.generateId;

/**
 * @author Joao Victor
 * @version 1.0
 * @since 06/08/2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {

    private String id = generateId();
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sentDate;
    private Status status;

}

