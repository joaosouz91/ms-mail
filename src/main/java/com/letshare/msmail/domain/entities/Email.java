package com.letshare.msmail.domain.entities;

import com.letshare.msmail.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Joao Victor
 * @version 1.0
 * @since 06/08/2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_email")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerRef;

    private String emailFrom;

    private String emailTo;

    private String subject;

    @Column(columnDefinition = "text")
    private String text;

    private LocalDateTime sentDate;

    private Status status;


}

