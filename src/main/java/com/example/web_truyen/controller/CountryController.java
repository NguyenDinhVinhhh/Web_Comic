package com.example.web_truyen.controller;

import com.example.web_truyen.dto.CountryDTO;
import com.example.web_truyen.entity.Country;
import com.example.web_truyen.form.country.CountryFilterForm;
import com.example.web_truyen.service.country.ICountryService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @DeleteMapping("/{id}")
    public void DeleteCountryById(@PathVariable int id)
    {
        countryService.DeleteCountryById(id);
    }

    @PostMapping("create")
    public Country createCountry(@RequestBody @Valid CountryDTO countryDTO) {
        return countryService.createCountry(countryDTO);
    }

    @PutMapping("/{id}")
    public void updateDepartment(@PathVariable("id")  int id , @RequestBody  CountryDTO countryDTO)
    {
        countryService.UpdateCountry(id,countryDTO);
    }

}
