package com.letshare.msmail.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static com.letshare.msmail.utils.IdSupplier.generateId;

/**
 * @author Joao Victor
 * @version 1.0
 * @since 06/08/2022
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Scheduler {

    private String key = generateId();
    private LocalDateTime lastExecutionStart;
    private boolean isRunning;
}
