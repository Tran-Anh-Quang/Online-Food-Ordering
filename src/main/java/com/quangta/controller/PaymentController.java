package com.quangta.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @GetMapping("/create-payment")
    public ResponseEntity<?> createPayment() throws UnsupportedEncodingException {

return null;
    }
}
