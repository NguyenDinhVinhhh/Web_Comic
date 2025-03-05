package com.example.web_truyen.controller;

import com.example.web_truyen.dto.CountryDTO;
import com.example.web_truyen.entity.Country;
import com.example.web_truyen.form.country.CountryFilterForm;
import com.example.web_truyen.service.ICountryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/countries")
@CrossOrigin("*")
public class CountryController {
    @Autowired
    private ICountryService countryService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping
    public List<CountryDTO> getAllCountry(CountryFilterForm form)
    {
        List<Country> countries = countryService.getAllCountry(form);
        return modelMapper.map(countries,new TypeToken<List<CountryDTO>>(){}.getType());
    }
}
