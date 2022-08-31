package com.bank.msyankicustomerbackend.models.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DataEvent<T>{

    private String id;

    private String process;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateEvent;

    private T data;
}
