package com.letshare.msmail.repositories;

import com.letshare.msmail.domain.entities.Email;
import com.letshare.msmail.domain.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author Joao Victor
 * @version 1.0
 * @since 06/08/2022
 */

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {

    List<Email> findByStatus(Status status, Pageable pageable);
}
