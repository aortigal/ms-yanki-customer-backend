package com.bank.msyankicustomerbackend.controllers;

import com.bank.msyankicustomerbackend.handler.ResponseHandler;
import com.bank.msyankicustomerbackend.mocks.CustomerMock;
import com.bank.msyankicustomerbackend.models.documents.Customer;
import com.bank.msyankicustomerbackend.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(CustomerRestController.class)
public class CustomerRestControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private CustomerService customerService;

    @Test
    void findAllTest() {

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(null);

        when(customerService.findAll()).thenReturn(Mono.just(responseHandler));

        webClient
                .get().uri("/api/customer")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ResponseHandler.class);

    }

    @Test
    void findByIdTest() {
        Customer customer = CustomerMock.random();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(customer);

        Mockito
                .when(customerService.find("62edbc767ba3a05551fb10d6"))
                .thenReturn(Mono.just(responseHandler));

        webClient.get().uri("/api/customer/{id}", "62edbc767ba3a05551fb10d6")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ResponseHandler.class);

        Mockito.verify(customerService, times(1)).find("62edbc767ba3a05551fb10d6");
    }

    @Test
    void createTest() {

        Customer customer = CustomerMock.random();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(customer);

        Mockito
                .when(customerService.create(customer)).thenReturn(Mono.just(responseHandler));

        webClient
                .post()
                .uri("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(customer))
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    void updateTest() {

        Customer customer = CustomerMock.random();

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(customer);

        Mockito
                .when(customerService.update("62edbc767ba3a05551fb10d6",customer)).thenReturn(Mono.just(responseHandler));

        webClient
                .put()
                .uri("/api/customer/{id}", "62edbc767ba3a05551fb10d6")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(customer))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void deleteTest() {

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.setMessage("Ok");
        responseHandler.setStatus(HttpStatus.OK);
        responseHandler.setData(null);

        Mockito
                .when(customerService.delete("62edbc767ba3a05551fb10d6"))
                .thenReturn(Mono.just(responseHandler));

        webClient.delete().uri("/api/customer/{id}", "62edbc767ba3a05551fb10d6")
                .exchange()
                .expectStatus().isOk();
    }
}
