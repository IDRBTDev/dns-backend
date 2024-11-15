package com.idrbt.dr.declaration.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.idrbt.dr.declaration.model.Login;

public interface DeclarationFormRepository extends JpaRepository<Login, Long> {
}
