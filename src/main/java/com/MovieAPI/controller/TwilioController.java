package com.MovieAPI.controller;

import com.MovieAPI.service.TwilioService;
import com.MovieAPI.twilioconfiguration.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/sms")
public class TwilioController {

    @Autowired
    private final TwilioService twilioService;

    @Autowired
    public TwilioController(TwilioService service) {
        this.twilioService = service;
    }

    @PostMapping
    public void sendSms(@Valid @RequestBody SmsRequest smsRequest) {
        twilioService.sendSms(smsRequest);
    }

}
