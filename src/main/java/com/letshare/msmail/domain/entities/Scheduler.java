package com.letshare.msmail.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author Joao Victor
 * @version 1.0
 * @since 06/08/2022
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tbl_scheduler")
public class Scheduler {

    @Id
    private String key;

    private LocalDateTime lastExecutionStart;

    private boolean isRunning;
}
