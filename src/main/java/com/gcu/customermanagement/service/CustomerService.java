package com.gcu.customermanagement.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gcu.customermanagement.entity.Customer;
import com.gcu.customermanagement.repository.CustomerRepository;

@Service
public class CustomerService {

    private static final Logger logger =
            LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        logger.info("CustomerService - getAllCustomers - Enter");

        try {
            List<Customer> customers = customerRepository.findAll();
            logger.info("CustomerService - getAllCustomers - Exit");
            return customers;
        } catch (Exception e) {
            logger.error("CustomerService - getAllCustomers - Error: {}", e.getMessage());
            throw e;
        }
    }

    public Customer getCustomerById(Long id) {
        logger.info("CustomerService - getCustomerById - Enter");

        try {
            Optional<Customer> customer = customerRepository.findById(id);
            logger.info("CustomerService - getCustomerById - Exit");
            return customer.orElse(null);
        } catch (Exception e) {
            logger.error("CustomerService - getCustomerById - Error: {}", e.getMessage());
            throw e;
        }
    }

    public Customer saveCustomer(Customer customer) {
        logger.info("CustomerService - saveCustomer - Enter");

        try {
            Customer savedCustomer = customerRepository.save(customer);
            logger.info("CustomerService - saveCustomer - Exit");
            return savedCustomer;
        } catch (Exception e) {
            logger.error("CustomerService - saveCustomer - Error: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteCustomer(Long id) {
        logger.info("CustomerService - deleteCustomer - Enter");

        try {
            customerRepository.deleteById(id);
            logger.info("CustomerService - deleteCustomer - Exit");
        } catch (Exception e) {
            logger.error("CustomerService - deleteCustomer - Error: {}", e.getMessage());
            throw e;
        }
    }
}