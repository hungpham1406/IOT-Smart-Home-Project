package com.iot.home.applicaton.service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@SpringBootTest
class AdafruitIOServiceImplTest {
    @MockBean
    RestTemplate restTemplate;
    @InjectMocks
    AdafruitIOServiceImpl adafruitIOService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAdafruitIOData() {
        String  feedKey = "humidstm32";
        String result = adafruitIOService.getAdafruitIOData(feedKey);
        assertNotNull(result);
    }
}