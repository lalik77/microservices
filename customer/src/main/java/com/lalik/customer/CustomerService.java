package com.lalik.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    public void registerCustomer(CustomerRegistrationRequest customerRegistrationRequest) {

        Customer customer = Customer.builder()
                .firstName(customerRegistrationRequest.firstName())
                .lastName(customerRegistrationRequest.lastName())
                .email(customerRegistrationRequest.email())
                .build();

        // todo : check if email is valid
        // todo : check if email is not taken
        customerRepository.saveAndFlush(customer);
        // todo : check if fraudster
        FraudCheskResponse fraudCheskResponse = restTemplate.getForObject(
                "http://localhost:8081/api/v1/fraud-check/{customerId}",
                FraudCheskResponse.class,
                customer.getId()
        );

        if(fraudCheskResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        customerRepository.save(customer);
        // todo : send notification
    }
}
