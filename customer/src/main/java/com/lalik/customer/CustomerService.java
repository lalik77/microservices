package com.lalik.customer;

import com.lalik.clients.fraud.FraudCheckResponse;
import com.lalik.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

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

        FraudCheckResponse fraudCheckResponse= fraudClient.isFraudster(customer.getId());
        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        customerRepository.save(customer);
        // todo : send notification
    }
}
