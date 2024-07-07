package com.example.financialassistance.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
public class Citizen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateOfRegistration;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "active_name_id", referencedColumnName = "id")
    private Name activeName;

    private LocalDate dateOfBirth;

    private String nationalInsuranceNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "active_residential_address_id", referencedColumnName = "id")
    private Address activeResidentialAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "active_correspondence_address_id", referencedColumnName = "id")
    private Address activeCorrespondenceAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "active_bank_account_id", referencedColumnName = "id")
    private BankAccount activeBankAccount;

    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
    private List<Name> nameHistory;

    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
    private List<Address> addressHistory;

    @OneToMany(mappedBy = "citizen", cascade = CascadeType.ALL)
    private List<BankAccount> bankAccountHistory;

    private boolean isProspect;

    public Citizen() {
        this.dateOfRegistration = LocalDate.now();
        this.isProspect = true;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public Name getActiveName() {
        return activeName;
    }

    public void setActiveName(Name activeName) {
        this.activeName = activeName;
        this.nameHistory.add(activeName);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(String nationalInsuranceNumber) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
        if (nationalInsuranceNumber != null && !nationalInsuranceNumber.isEmpty()) {
            this.isProspect = false;
        }
    }

    public Address getActiveResidentialAddress() {
        return activeResidentialAddress;
    }

    public void setActiveResidentialAddress(Address activeResidentialAddress) {
        this.activeResidentialAddress = activeResidentialAddress;
        this.addressHistory.add(activeResidentialAddress);
    }

    public Address getActiveCorrespondenceAddress() {
        return activeCorrespondenceAddress;
    }

    public void setActiveCorrespondenceAddress(Address activeCorrespondenceAddress) {
        this.activeCorrespondenceAddress = activeCorrespondenceAddress;
        this.addressHistory.add(activeCorrespondenceAddress);
    }

    public BankAccount getActiveBankAccount() {
        return activeBankAccount;
    }

    public void setActiveBankAccount(BankAccount activeBankAccount) {
        this.activeBankAccount = activeBankAccount;
        this.bankAccountHistory.add(activeBankAccount);
    }

    public int getAge() {
        return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public void addName(Name name) {
        this.setActiveName(name);
    }

    public void addAddress(Address address) {
        if (address.isResidential()) {
            this.setActiveResidentialAddress(address);
        } else {
            this.setActiveCorrespondenceAddress(address);
        }
    }

    public void addBankAccount(BankAccount bankAccount) {
        this.setActiveBankAccount(bankAccount);
    }
}
