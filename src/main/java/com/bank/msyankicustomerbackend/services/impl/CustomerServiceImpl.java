package com.bank.msyankicustomerbackend.services.impl;

import com.bank.msyankicustomerbackend.handler.ResponseHandler;
import com.bank.msyankicustomerbackend.models.dao.CustomerDao;
import com.bank.msyankicustomerbackend.models.documents.Customer;
import com.bank.msyankicustomerbackend.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao dao;
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public Mono<ResponseHandler> findAll() {
        log.info("[INI] findAll Customer");
        return dao.findAll()
                .doOnNext(customer -> log.info(customer.toString()))
                .collectList()
                .map(customers -> new ResponseHandler("Done", HttpStatus.OK, customers))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] findAll Customer"));
    }

    @Override
    public Mono<ResponseHandler> find(String id) {
        log.info("[INI] find Customer");
        return dao.findById(id)
                .doOnNext(customer -> log.info(customer.toString()))
                .map(customer -> new ResponseHandler("Done", HttpStatus.OK, customer))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] find Customer"));
    }

    @Override
    public Mono<ResponseHandler> create(Customer customer) {
        log.info("[INI] create Customer");
        customer.setDateRegister(LocalDateTime.now());
        return dao.save(customer)
                .doOnNext(c -> {
                    log.info(c.toString());
                })
                .map(c -> new ResponseHandler("Done", HttpStatus.OK, c))
                .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)))
                .doFinally(fin -> log.info("[END] create Customer"));
    }

    @Override
    public Mono<ResponseHandler> update(String id, Customer c) {
        log.info("[INI] update Customer");
        return dao.existsById(id).flatMap(check -> {
            if (check){
                c.setDateUpdate(LocalDateTime.now());
                return dao.save(c)
                        .doOnNext(customer -> log.info(customer.toString()))
                        .map(customer -> new ResponseHandler("Done", HttpStatus.OK, customer)                )
                        .onErrorResume(error -> Mono.just(new ResponseHandler(error.getMessage(), HttpStatus.BAD_REQUEST, null)));
            }
            else
                return Mono.just(new ResponseHandler("Not found", HttpStatus.NOT_FOUND, null));

        }).doFinally(fin -> log.info("[END] update Customer"));
    }

    @Override
    public Mono<ResponseHandler> delete(String id) {
        log.info("[INI] delete customer");

        return dao.existsById(id).flatMap(check -> {
            if (check)
                return dao.deleteById(id).then(Mono.just(new ResponseHandler("Done", HttpStatus.OK, null)));
            else
                return Mono.just(new ResponseHandler("Not found", HttpStatus.NOT_FOUND, null));
        }).doFinally(fin -> log.info("[END] delete customer"));
    }
}
