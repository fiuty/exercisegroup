package com.dayue.userservice.external.feign.service.impl;

import com.dayue.userservice.external.feign.AmapOriginalFeignService;
import com.dayue.userservice.external.feign.service.RemoteApiService;
import com.dayue.userservice.external.feign.service.coder.JacksonDecoder;
import com.dayue.userservice.external.feign.service.coder.JacksonEncoder;
import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhengdayue
 * @date: 2021-08-13
 */
@Service
public class RemoteApiServiceImpl implements RemoteApiService {

    @Autowired
    private JacksonDecoder jacksonDecoder;

    @Autowired
    private JacksonEncoder jacksonEncoder;

    @Override
    public AmapOriginalFeignService amapOriginalFeignService() {
        return Feign.builder().decoder(jacksonDecoder).encoder(jacksonEncoder).target(AmapOriginalFeignService.class,"https://restapi.amap.com");
    }
}
