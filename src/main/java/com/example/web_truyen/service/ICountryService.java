package com.example.web_truyen.service;

import com.example.web_truyen.entity.Country;
import com.example.web_truyen.form.country.CountryFilterForm;

import java.util.List;

public interface ICountryService {
    List<Country> getAllCountry(CountryFilterForm form);
}
