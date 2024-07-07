package com.example.financialassistance.controller;

import com.example.financialassistance.model.*;
import com.example.financialassistance.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/citizens")
public class CitizenController {

    @Autowired
    private CitizenService citizenService;

    @PostMapping
    public ResponseEntity<Citizen> registerCitizen(@RequestBody Citizen citizen) {
        Citizen registeredCitizen = citizenService.registerCitizen(citizen);
        return ResponseEntity.ok(registeredCitizen);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Citizen> getCitizen(@PathVariable Long id) {
        Optional<Citizen> citizenOptional = citizenService.findById(id);
        if (citizenOptional.isPresent()) {
            return ResponseEntity.ok(citizenOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/name")
    public ResponseEntity<Void> addName(@PathVariable Long id, @RequestBody Name name) {
        citizenService.addName(id, name);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/address")
    public ResponseEntity<Void> addAddress(@PathVariable Long id, @RequestBody Address address) {
        citizenService.addAddress(id, address);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/bankAccount")
    public ResponseEntity<Void> addBankAccount(@PathVariable Long id, @RequestBody BankAccount bankAccount) {
        citizenService.addBankAccount(id, bankAccount);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/nationalInsurance")
    public ResponseEntity<Void> updateNationalInsuranceNumber(@PathVariable Long id, @RequestBody String nationalInsuranceNumber) {
        citizenService.updateNationalInsuranceNumber(id, nationalInsuranceNumber);
        return ResponseEntity.ok().build();
    }
}
