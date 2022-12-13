package com.example.retailrwdcustomcalc;

import com.example.retailrwdcustomcalc.common.TestDataGenerator;
import com.example.retailrwdcustomcalc.entity.Customer;
import com.example.retailrwdcustomcalc.repository.CustomerRepository;
import com.example.retailrwdcustomcalc.repository.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RetailRwdCustomCalcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class RetailRwdCustomCalcApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    ObjectMapper mapper;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    private final List<Customer> expected = TestDataGenerator.getCustomers();

    @BeforeEach
    public void setUp() {
        customerRepository.deleteAll();
        transactionRepository.deleteAll();
        customerRepository.saveAll(expected);
    }

    @Test
    public void getAllRewards() throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(generateURL("/Rewards"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

    }

    @Test
    public void getRewardsById_Success() throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        List<Customer> customers = customerRepository.findAll();
        ResponseEntity<String> response = restTemplate.exchange(generateURL("/Rewards/" + customers.get(0).getId()), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());

    }

    @Test
    public void getRewardsById_ThrowsException() throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(generateURL("/Rewards/123"), HttpMethod.GET, entity, String.class);

        String expected = "Customer doesn't exist 123";

        assertEquals(HttpStatusCode.valueOf(404), response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void getAllTransactions_Success() throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(generateURL("/transactions"), HttpMethod.GET, entity, String.class);

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }


    private String generateURL(String uri) {
        return "http://localhost:" + port + uri;
    }
}
