package com.example.financialassistance.service;

import com.example.financialassistance.model.*;
import com.example.financialassistance.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private NameRepository nameRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public Citizen registerCitizen(Citizen citizen) {
        return citizenRepository.save(citizen);
    }

    public Optional<Citizen> findById(Long id) {
        return citizenRepository.findById(id);
    }

    public void addName(Long citizenId, Name name) {
        Optional<Citizen> citizenOptional = citizenRepository.findById(citizenId);
        if (citizenOptional.isPresent()) {
            Citizen citizen = citizenOptional.get();
            name.setCitizen(citizen);
            citizen.addName(name);
            citizenRepository.save(citizen);
        }
    }

    public void addAddress(Long citizenId, Address address) {
        Optional<Citizen> citizenOptional = citizenRepository.findById(citizenId);
        if (citizenOptional.isPresent()) {
            Citizen citizen = citizenOptional.get();
            address.setCitizen(citizen);
            citizen.addAddress(address);
            citizenRepository.save(citizen);
        }
    }

    public void addBankAccount(Long citizenId, BankAccount bankAccount) {
        Optional<Citizen> citizenOptional = citizenRepository.findById(citizenId);
        if (citizenOptional.isPresent()) {
            Citizen citizen = citizenOptional.get();
            bankAccount.setCitizen(citizen);
            citizen.addBankAccount(bankAccount);
            citizenRepository.save(citizen);
        }
    }

    public void updateNationalInsuranceNumber(Long citizenId, String nationalInsuranceNumber) {
        Optional<Citizen> citizenOptional = citizenRepository.findById(citizenId);
        if (citizenOptional.isPresent()) {
            Citizen citizen = citizenOptional.get();
            citizen.setNationalInsuranceNumber(nationalInsuranceNumber);
            citizenRepository.save(citizen);
        }
    }
}
