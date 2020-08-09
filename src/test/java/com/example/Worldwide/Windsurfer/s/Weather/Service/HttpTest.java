package com.example.Worldwide.Windsurfer.s.Weather.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private TestRestTemplate restTemplate2;

    @Test
    public void errorTestFormatDate() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/weather?date=2020-08-111",
                String.class)).contains("Given date have wrong format!");

    }

    @Test
    public void errorTestDateRange() throws Exception {
        assertThat(this.restTemplate2.getForObject("http://localhost:" + port + "/weather?date=2020-08-30",
                String.class)).contains("Given date is not in the range of weather forecast!");

    }
}
