package com.bank.msyankicustomerbackend.models.dao;

import com.bank.msyankicustomerbackend.models.documents.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerDao extends ReactiveMongoRepository<Customer, String> {
}
