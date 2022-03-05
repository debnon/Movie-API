package com.MovieAPI.service;

import com.MovieAPI.twilioconfiguration.SmsRequest;
import com.MovieAPI.twilioconfiguration.SmsSender;
import com.MovieAPI.twilioconfiguration.TwilioSmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


@org.springframework.stereotype.Service
public class TwilioService {

    private final SmsSender smsSender;

    @Autowired
    public TwilioService( TwilioSmsSender smsSender) {
        this.smsSender = smsSender;
    }

    public void sendSms(SmsRequest smsRequest) {
        smsSender.sendSms(smsRequest);
    }
}
