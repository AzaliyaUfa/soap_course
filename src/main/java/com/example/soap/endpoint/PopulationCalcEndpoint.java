package com.example.soap.endpoint;

import com.example.soap.client.CalcClient;
import com.example.soap.exception.BusinessException;
import com.example.soap.repo.CountryRepo;
import demo.example.com.PopulationSumRequest;
import demo.example.com.PopulationSumResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PopulationCalcEndpoint {

    private static final String NS_NAME = "http://com.example.demo";

    private final CountryRepo countryRepo;

    private final CalcClient calcClient;

    public PopulationCalcEndpoint(CountryRepo countryRepo,
                                  CalcClient calcClient) {
        this.countryRepo = countryRepo;
        this.calcClient = calcClient;
    }

    @PayloadRoot(namespace = NS_NAME, localPart = "PopulationSumRequest")
    @ResponsePayload
    public PopulationSumResponse getPopulatinSum(@RequestPayload PopulationSumRequest request) {
        PopulationSumResponse response = new PopulationSumResponse();
        int sum = 0;
        for (int i = 0; i < request.getIsoCode().size(); i++) {
            String isoCode = request.getIsoCode().get(i);
            int population = countryRepo.findCountry(isoCode)
                    .orElseThrow(() -> new BusinessException("Country with ISO code = " + isoCode + " was not found, try one of these: "
                            + countryRepo.getCountriesISOCodes()))
                    .getPopulation();
            sum = calcClient.add(sum, population);
        }
        response.setPopulationSum(sum);
        return response;
    }

}
