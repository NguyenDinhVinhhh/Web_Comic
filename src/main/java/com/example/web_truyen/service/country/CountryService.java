package com.example.web_truyen.service.country;

import com.example.web_truyen.dto.CountryDTO;
import com.example.web_truyen.entity.Country;
import com.example.web_truyen.form.country.CountryFilterForm;
import com.example.web_truyen.repository.ICountryRepository;
import com.example.web_truyen.specification.CountrySpeccification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryService implements ICountryService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ICountryRepository countryRepository;
    @Override
    public List<Country> getAllCountry(CountryFilterForm form) {
        Specification<Country> where = CountrySpeccification.buildwhere(form);
        return countryRepository.findAll(where);
    }

    @Override
    public Country createCountry(CountryDTO countryDTO) {
        if (countryRepository.existsByName(countryDTO.getName())) {
            throw new RuntimeException("Country already exists!");
        }

        // Chuyển từ DTO -> Entity trước khi lưu vào database
        Country country = modelMapper.map(countryDTO, Country.class);
        return countryRepository.save(country); // Trả về Country đã được lưu
    }


    @Override
    public void DeleteCountryById(int id) {
        countryRepository.deleteById(id);
    }

    @Override
    public void UpdateCountry(int id, Country country) {

    }

}
