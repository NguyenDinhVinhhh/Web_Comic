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
import java.util.Optional;

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
        if (!countryRepository.existsById(id))
        {
            throw new RuntimeException("vui long nhap id khac");
        }
        countryRepository.deleteById(id);
    }

    @Override
    public void UpdateCountry(int id, CountryDTO countryDTO) {
        // Kiểm tra ID có tồn tại không
        if (!countryRepository.existsById(id)) {
            throw new RuntimeException("Country with ID " + id + " not found");
        }

        // Kiểm tra tên có bị trùng với quốc gia khác không
        Optional<Country> existingCountry = countryRepository.findByName(countryDTO.getName());
        if (existingCountry.isPresent() && existingCountry.get().getId() != id) {
            throw new RuntimeException("Country name '" + countryDTO.getName() + "' already exists");
        }

        // Chuyển đổi DTO -> Entity
        Country country = modelMapper.map(countryDTO, Country.class);
        country.setId(id);

        // Cập nhật quốc gia
        countryRepository.save(country);
    }



}
