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
        logger.info("Opening home page");
        return "index";
    }

    @GetMapping("/customers")
    public String showCustomerList(Model model) {
        logger.info("Loading customer list");
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @GetMapping("/customers/new")
    public String showAddCustomerForm(Model model) {
        logger.info("Opening add customer form");
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping("/customers/save")
    public String saveCustomer(@ModelAttribute Customer customer) {
        logger.info("Saving customer");
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{id}")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        logger.info("Opening edit form for customer ID: {}", id);
        model.addAttribute("customer", customerService.getCustomerById(id));
        return "customer-form";
    }

    @GetMapping("/customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        logger.info("Deleting customer ID: {}", id);
        customerService.deleteCustomer(id);
        return "redirect:/customers";
    }
}