package com.auth.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.stereotype.Service;


@Service
public class JsonService extends ObjectMapper {
    public JsonService(){
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        registerModule(new JodaModule());
    }
}
