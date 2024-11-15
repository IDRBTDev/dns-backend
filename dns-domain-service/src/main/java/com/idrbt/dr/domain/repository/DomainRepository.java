package com.idrbt.dr.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idrbt.dr.domain.model.Domain;

import java.util.Optional;

public interface DomainRepository extends JpaRepository<Domain, Long> {
    Optional<Domain> findByApplicationId(String applicationId);
}
