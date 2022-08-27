package com.bank.msyankicustomerbackend.services;

import com.bank.msyankicustomerbackend.handler.ResponseHandler;
import com.bank.msyankicustomerbackend.mocks.CustomerMock;
import com.bank.msyankicustomerbackend.models.dao.CustomerDao;
import com.bank.msyankicustomerbackend.models.documents.Customer;
import com.bank.msyankicustomerbackend.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerDao dao;

    @Test
    void findAllTest() {

        Customer customer = CustomerMock.random();

        Mockito.when(dao.findAll()).thenReturn(Flux.just(customer));

        Mono<ResponseHandler> responseHandlerMono = customerService.findAll();

        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getData() !=null)
                .verifyComplete();
    }

    @Test
    void createTest() {

        Customer customer = CustomerMock.random();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(customer);

        //Mockito.when(dao.save(active)).thenReturn(Mono.just(active));
        Mockito.when(dao.save(customer)).thenReturn(Mono.just(customer));
    }

    @Test
    void findTest() {

        Customer customer = CustomerMock.random();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(customer);

        Mockito.when(dao.findById("62edbc767ba3a05551fb10d6"))
                .thenReturn(Mono.just(customer));

        Mono<ResponseHandler> responseHandlerMono = customerService.find("62edbc767ba3a05551fb10d6");

        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getData() !=null)
                .verifyComplete();
    }

    @Test
    void updateTest() {

        Customer customer = CustomerMock.random();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(customer);

        Mockito.when(dao.existsById("62edbc767ba3a05551fb10d6"))
                .thenReturn(Mono.just(true));

        Mockito.when(dao.save(customer))
                .thenReturn(Mono.just(customer));

        Mono<ResponseHandler> responseHandlerMono = customerService
                .update("62edbc767ba3a05551fb10d6", customer);

        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getData() !=null)
                .verifyComplete();
    }

    @Test
    void updateFoundTest() {

        Customer customer = CustomerMock.random();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(customer);

        Mockito.when(dao.existsById("62edbc767ba3a05551fb10d6"))
                .thenReturn(Mono.just(false));

        Mono<ResponseHandler> responseHandlerMono = customerService
                .update("62edbc767ba3a05551fb10d6", customer);

        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getMessage().equals("Not found"))
                .verifyComplete();
    }

    @Test
    void deleteTest() {

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(null);

        Mockito.when(dao.existsById("62edbc767ba3a05551fb10d6"))
                .thenReturn(Mono.just(true));

        Mockito.when(dao.deleteById("62edbc767ba3a05551fb10d6")).thenReturn(Mono.empty());

        Mono<ResponseHandler> responseHandlerMono = customerService
                .delete("62edbc767ba3a05551fb10d6");

        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getMessage().equals("Done"))
                .verifyComplete();
    }

    @Test
    void deleteFoundTest() {

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(null);

        Mockito.when(dao.existsById("62edbc767ba3a05551fb10d6"))
                .thenReturn(Mono.just(false));

        Mono<ResponseHandler> responseHandlerMono = customerService
                .delete("62edbc767ba3a05551fb10d6");

        StepVerifier.create(responseHandlerMono)
                .expectNextMatches(response -> response.getMessage().equals("Not found"))
                .verifyComplete();
    }
}
