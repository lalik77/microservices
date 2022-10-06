package com.lalik.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email) {

}
