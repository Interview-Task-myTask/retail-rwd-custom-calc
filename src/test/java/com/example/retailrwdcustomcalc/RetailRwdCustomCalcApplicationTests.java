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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RetailRwdCustomCalcApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

        String expected = generateExpectedString();

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(mapper.readTree(expected), mapper.readTree(response.getBody()));

    }

    @Test
    public void getRewardsById_Success() throws JsonProcessingException {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        List<Customer> customers = customerRepository.findAll();
        ResponseEntity<String> response = restTemplate.exchange(generateURL("/Rewards/" + customers.get(0).getId()), HttpMethod.GET, entity, String.class);

        String expected = generateExpectedFirstCustomerString();

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
        String expected = generateExpectedTransactionsString();

        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
    }

    private String generateExpectedTransactionsString() {
        return "[{\"id\":10,\"transactionTotal\":150.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"},{\"id\":11,\"transactionTotal\":50.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"},{\"id\":12,\"transactionTotal\":30.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"},{\"id\":13,\"transactionTotal\":150.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"},{\"id\":14,\"transactionTotal\":50.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"},{\"id\":15,\"transactionTotal\":30.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"},{\"id\":16,\"transactionTotal\":150.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"},{\"id\":17,\"transactionTotal\":50.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"},{\"id\":18,\"transactionTotal\":30.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"}]";
    }

    private String generateExpectedFirstCustomerString() {
        return "{\"id\":4,\"name\":\"King\",\"rewards\":310,\"transactions\":[" + "{\"id\":10,\"transactionTotal\":150.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"}," + "{\"id\":11,\"transactionTotal\":50.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"}," + "{\"id\":12,\"transactionTotal\":30.0,\"transactionDate\":\"2022-12-12T06:00:00.000+00:00\"}]}";
    }

    private String generateExpectedString() {
        return "[ {\n" + "  \"id\" : 4,\n" + "  \"name\" : \"King\",\n" + "  \"rewards\" : 310,\n" + "  \"transactions\" : [ {\n" + "    \"id\" : 10,\n" + "    \"transactionTotal\" : 150.00,\n" + "    \"transactionDate\" : \"2022-12-12T06:00:00.000+00:00\"\n" + "  }, {\n" + "    \"id\" : 11,\n" + "    \"transactionTotal\" : 50.00,\n" + "    \"transactionDate\" : \"2022-12-12T06:00:00.000+00:00\"\n" + "  }, {\n" + "    \"id\" : 12,\n" + "    \"transactionTotal\" : 30.00,\n" + "    \"transactionDate\" : \"2022-12-12T06:00:00.000+00:00\"\n" + "  } ]\n" + "}, {\n" + "  \"id\" : 5,\n" + "  \"name\" : \"Queen\",\n" + "  \"rewards\" : 310,\n" + "  \"transactions\" : [ {\n" + "    \"id\" : 13,\n" + "    \"transactionTotal\" : 150.00,\n" + "    \"transactionDate\" : \"2022-12-12T06:00:00.000+00:00\"\n" + "  }, {\n" + "    \"id\" : 14,\n" + "    \"transactionTotal\" : 50.00,\n" + "    \"transactionDate\" : \"2022-12-12T06:00:00.000+00:00\"\n" + "  }, {\n" + "    \"id\" : 15,\n" + "    \"transactionTotal\" : 30.00,\n" + "    \"transactionDate\" : \"2022-12-12T06:00:00.000+00:00\"\n" + "  } ]\n" + "}, {\n" + "  \"id\" : 6,\n" + "  \"name\" : \"Jack\",\n" + "  \"rewards\" : 310,\n" + "  \"transactions\" : [ {\n" + "    \"id\" : 16,\n" + "    \"transactionTotal\" : 150.00,\n" + "    \"transactionDate\" : \"2022-12-12T06:00:00.000+00:00\"\n" + "  }, {\n" + "    \"id\" : 17,\n" + "    \"transactionTotal\" : 50.00,\n" + "    \"transactionDate\" : \"2022-12-12T06:00:00.000+00:00\"\n" + "  }, {\n" + "    \"id\" : 18,\n" + "    \"transactionTotal\" : 30.00,\n" + "    \"transactionDate\" : \"2022-12-12T06:00:00.000+00:00\"\n" + "  } ]\n" + "} ]";
    }

    private String generateURL(String uri) {
        return "http://localhost:" + port + uri;
    }
}
