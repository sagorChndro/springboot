package com.sagor.springcrudrestapiemailsecur.repository;

import com.sagor.springcrudrestapiemailsecur.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
