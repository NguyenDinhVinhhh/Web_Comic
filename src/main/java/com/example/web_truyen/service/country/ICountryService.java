package com.example.web_truyen.service.country;

import com.example.web_truyen.dto.CountryDTO;
import com.example.web_truyen.entity.Country;
import com.example.web_truyen.form.country.CountryFilterForm;

import java.util.List;

public interface ICountryService {
    List<Country> getAllCountry(CountryFilterForm form);
    Country createCountry(CountryDTO countryDTO);
    void DeleteCountryById(int id);
    void UpdateCountry(int id, Country country);

}
