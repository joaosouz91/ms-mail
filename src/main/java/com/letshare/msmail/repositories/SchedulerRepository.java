package com.letshare.msmail.repositories;

import com.letshare.msmail.domain.entities.Scheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Joao Victor
 * @version 1.0
 * @since 06/08/2022
 */

@Repository
public interface SchedulerRepository extends JpaRepository<Scheduler, String> {

    Optional<Scheduler> findByKey(String key);

}
