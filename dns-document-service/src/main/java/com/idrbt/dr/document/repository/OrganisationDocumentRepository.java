package com.idrbt.dr.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idrbt.dr.document.model.OrganisationDocument;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationDocumentRepository extends JpaRepository<OrganisationDocument, Integer> {
}
