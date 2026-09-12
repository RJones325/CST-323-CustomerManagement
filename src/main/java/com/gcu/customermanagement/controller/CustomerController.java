package com.gcu.customermanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.customermanagement.entity.Customer;
import com.gcu.customermanagement.service.CustomerService;

@Controller
public class CustomerController {

    private static final Logger logger =
            LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String showHomePage() {
        logger.info("CustomerController - showHomePage - Enter");

        try {
            logger.info("CustomerController - showHomePage - Exit");
            return "index";
        } catch (Exception e) {
            logger.error("CustomerController - showHomePage - Error: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/customers")
    public String showCustomerList(Model model) {
        logger.info("CustomerController - showCustomerList - Enter");

        try {
            model.addAttribute("customers", customerService.getAllCustomers());
            logger.info("CustomerController - showCustomerList - Exit");
            return "customers";
        } catch (Exception e) {
            logger.error("CustomerController - showCustomerList - Error: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/customers/new")
    public String showAddCustomerForm(Model model) {
        logger.info("CustomerController - showAddCustomerForm - Enter");

        try {
            model.addAttribute("customer", new Customer());
            logger.info("CustomerController - showAddCustomerForm - Exit");
            return "customer-form";
        } catch (Exception e) {
            logger.error("CustomerController - showAddCustomerForm - Error: {}", e.getMessage());
            throw e;
        }
    }

    @PostMapping("/customers/save")
    public String saveCustomer(@ModelAttribute Customer customer) {
        logger.info("CustomerController - saveCustomer - Enter");

        try {
            customerService.saveCustomer(customer);
            logger.info("CustomerController - saveCustomer - Exit");
            return "redirect:/customers";
        } catch (Exception e) {
            logger.error("CustomerController - saveCustomer - Error: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/customers/edit/{id}")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        logger.info("CustomerController - showEditCustomerForm - Enter");

        try {
            model.addAttribute("customer", customerService.getCustomerById(id));
            logger.info("CustomerController - showEditCustomerForm - Exit");
            return "customer-form";
        } catch (Exception e) {
            logger.error("CustomerController - showEditCustomerForm - Error: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        logger.info("CustomerController - deleteCustomer - Enter");

        try {
            customerService.deleteCustomer(id);
            logger.info("CustomerController - deleteCustomer - Exit");
            return "redirect:/customers";
        } catch (Exception e) {
            logger.error("CustomerController - deleteCustomer - Error: {}", e.getMessage());
            throw e;
        }
    }
}