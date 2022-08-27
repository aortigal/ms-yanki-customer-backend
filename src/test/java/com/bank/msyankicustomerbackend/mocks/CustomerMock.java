package com.bank.msyankicustomerbackend.mocks;

import com.bank.msyankicustomerbackend.models.documents.Customer;

import java.util.UUID;

public class CustomerMock {

    public static Customer random(){
        Customer customer = new Customer();
        customer.setId(UUID.randomUUID().toString());
        customer.setName(UUID.randomUUID().toString());
        customer.setDocumentNumber(UUID.randomUUID().toString());
        customer.setImei(UUID.randomUUID().toString());
        customer.setPhoneNumber(UUID.randomUUID().toString());
        customer.setEmail(UUID.randomUUID().toString());
        return customer;
    }
}
