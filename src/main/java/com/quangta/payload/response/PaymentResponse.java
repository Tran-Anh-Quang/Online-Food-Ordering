package com.quangta.payload.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentResponse implements Serializable {
    private String status;
    private String message;
    private String payment_url;
}
