package com.idrbt.dr.declaration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.idrbt.dr.declaration.model.Login;
import com.idrbt.dr.declaration.repository.DeclarationFormRepository;

import java.util.List;

@RestController
@RequestMapping("/dr/login")
@CrossOrigin(origins = "http://localhost:3000")
public class DeclarationFormController {

    @Autowired
    private DeclarationFormRepository declarationFormRepository;

    @GetMapping
    public List<Login> getAllForms() {
        return declarationFormRepository.findAll();
    }

    @PostMapping
    public Login saveForm(@RequestBody Login login) {
        //System.out.println(login);
    	//TODO: change to UID
        login.setApplicationId("IDRBT24" + String.valueOf((int) ((Math.random() * 900000) + 100000)));
        return declarationFormRepository.save(login);
    }
}
