package com.example.soap.repo;

import demo.example.com.Country;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CountryRepo {

   private List<Country> countries = new ArrayList<>();

   public List<String> getCountriesISOCodes() {
      return countriesISOCodes;
   }

   private List<String> countriesISOCodes = new ArrayList<>();

   @PostConstruct
   private void initCountries() {
      Country russia = new Country();
      russia.setPopulation(147_182_123);
      russia.setCapital("Moscow");
      russia.setName("Russia");
      russia.setIsoCode("RU");
      countries.add(russia);

      Country china = new Country();
      china.setPopulation(1_425_862_000);
      china.setCapital("Beijing");
      china.setName("China");
      china.setIsoCode("CN");
      countries.add(china);

      Country usa = new Country();
      usa.setPopulation(333_287_557);
      usa.setCapital("Washington");
      usa.setName("USA");
      usa.setIsoCode("US");
      countries.add(usa);

      Country turkey = new Country();
      turkey.setPopulation(84_680_273);
      turkey.setCapital("Ankara");
      turkey.setName("Turkey");
      turkey.setIsoCode("TR");
      countries.add(turkey);

      Country australia = new Country();
      australia.setPopulation(26_021_936);
      australia.setCapital("Canberra");
      australia.setName("Australia");
      australia.setIsoCode("AU");
      countries.add(australia);

      Country egypt = new Country();
      egypt.setPopulation(104_243_583);
      egypt.setCapital("Cairo");
      egypt.setName("Egypt");
      egypt.setIsoCode("EG");
      countries.add(egypt);

      countriesISOCodes = countries.stream()
              .map(Country::getIsoCode)
              .collect(Collectors.toList());
   }

   public Optional<Country> findCountry(String isoCode) {
      return countries.stream()
              .filter(c -> c.getIsoCode().equalsIgnoreCase(isoCode))
              .findFirst();
   }

}
