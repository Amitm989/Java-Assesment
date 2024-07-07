package com.example.financialassistance.repository;

import com.example.financialassistance.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
