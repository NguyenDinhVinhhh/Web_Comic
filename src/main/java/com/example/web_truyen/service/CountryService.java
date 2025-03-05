package com.example.web_truyen.service;

import com.example.web_truyen.entity.Country;
import com.example.web_truyen.form.country.CountryFilterForm;
import com.example.web_truyen.repository.ICountryRepository;
import com.example.web_truyen.specification.CountrySpeccification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryService implements ICountryService{
    @Autowired
    private ICountryRepository countryRepository;
    @Override
    public List<Country> getAllCountry(CountryFilterForm form) {
        Specification<Country> where = CountrySpeccification.buildwhere(form);
        return countryRepository.findAll(where);
    }
}
