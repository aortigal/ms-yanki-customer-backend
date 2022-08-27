package com.bank.msyankicustomerbackend.models.documents;

import com.bank.msyankicustomerbackend.models.utils.Audit;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@Document(collection = "customers")
public class Customer extends Audit {

    @Id
    private String id;

    @NotNull(message = "documentNumber must not be null")
    private String documentNumber;

    @NotNull(message = "phoneNumber must not be null")
    private String phoneNumber;

    @NotNull(message = "name must not be null")
    private String name;

    @NotNull(message = "imei must not be null")
    private String imei;

    @NotNull(message = "email must not be null")
    private String email;

}
