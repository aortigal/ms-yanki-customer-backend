package com.bank.msyankicustomerbackend.services;

import com.bank.msyankicustomerbackend.handler.ResponseHandler;
import com.bank.msyankicustomerbackend.models.documents.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<ResponseHandler> findAll();

    Mono<ResponseHandler> find(String id);

    Mono<ResponseHandler> create(Customer customer);

    Mono<ResponseHandler> update(String id, Customer customer);

    Mono<ResponseHandler> delete(String id);

    Mono<ResponseHandler> findByPhoneNumber(String phoneNumber);
}
