package com.task.service;

import com.task.mapper.CountryMapper;
import com.task.model.Country;
import com.task.model.dto.CountryResponse;
import com.task.model.dto.UpdateCountryRequest;
import com.task.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public List<CountryResponse> getAll() {
        return countryRepository.findAll().stream()
                .map(countryMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public CountryResponse create(String name) {
        Country country = Country.builder()
                .name(name)
                .build();

        countryRepository.save(country);
        return countryMapper.entityToDto(country);
    }

    @Transactional
    public void update(UpdateCountryRequest updateCountryRequest) {
        countryRepository.findById(updateCountryRequest.getId())
                .ifPresent(country -> countryRepository.updateName(country.getId(), updateCountryRequest.getName()));
    }

    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
