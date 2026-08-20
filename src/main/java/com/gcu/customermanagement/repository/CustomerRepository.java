package com.gcu.customermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gcu.customermanagement.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}