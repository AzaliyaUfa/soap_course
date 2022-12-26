package com.example.soap.endpoint;


import com.example.soap.exception.BusinessException;
import com.example.soap.repo.CountryRepo;
import demo.example.com.Country;
import demo.example.com.CountryRequest;
import demo.example.com.CountryResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

    private static final String NS_NAME = "http://com.example.demo";

    private final CountryRepo countryRepo;

    public CountryEndpoint(CountryRepo countryRepo) {
        this.countryRepo = countryRepo;
    }

    @PayloadRoot(namespace = NS_NAME, localPart = "CountryRequest")
    @ResponsePayload
    public CountryResponse findCountry(@RequestPayload CountryRequest request) {
        CountryResponse response = new CountryResponse();
        Country country = countryRepo.findCountry(request.getIsoCode())
                .orElseThrow(() -> new BusinessException("NotFound"));
        response.setCountry(country);
        return response;
    }

}
