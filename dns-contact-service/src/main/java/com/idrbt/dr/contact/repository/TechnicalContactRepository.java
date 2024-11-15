package com.idrbt.dr.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idrbt.dr.contact.model.TechnicalContact;

import java.util.Optional;

public interface TechnicalContactRepository extends JpaRepository<TechnicalContact, Long> {
    Optional<TechnicalContact> findByApplicationId(String applicationId);
}
