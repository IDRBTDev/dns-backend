package com.idrbt.dr.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idrbt.dr.contact.model.AdministrativeContact;

import java.util.Optional;

public interface AdministrativeContactRepository extends JpaRepository<AdministrativeContact, Long> {
    Optional<AdministrativeContact> findByApplicationId(String applicationId);
}
