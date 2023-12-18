package com.qpay.usermanager.controller;

import com.qpay.usermanager.model.dto.customer.CustomerModification;
import com.qpay.usermanager.model.entity.customer.CustomerEntity;
import com.qpay.usermanager.service.CustomerService;
import com.qpay.usermanager.utility.Paths;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Paths.CUSTOMER_PATH)
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("{id}")
    public CustomerEntity getCustomer(@PathVariable final long id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public CustomerEntity addCustomer(@Valid @RequestBody final CustomerModification customerModification) {
        return customerService.addCustomer(customerModification);
    }

    @PutMapping("{id}")
    public CustomerEntity updateCustomer(@Valid @RequestBody final CustomerModification customerModification,
                                         @PathVariable final long id) {
        return customerService.updateCustomer(customerModification, id);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable final long id) {
        customerService.deleteCustomer(id);
    }
}
