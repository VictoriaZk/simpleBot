package com.task.service;

import com.task.exception.ObjectNotFoundException;
import com.task.mapper.CityMapper;
import com.task.model.City;
import com.task.model.Country;
import com.task.model.dto.CityResponse;
import com.task.model.dto.CreateCityRequest;
import com.task.model.dto.UpdateCityRequest;
import com.task.repository.CityRepository;
import com.task.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public List<CityResponse> getAll() {
        return cityRepository.findAll().stream()
                .map(cityMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public CityResponse create(CreateCityRequest cityRequest) {
        Country country = getCountryByName(cityRequest.getCountry());

        City newCity = City.builder()
                .name(cityRequest.getName())
                .description(cityRequest.getDescription())
                .recommendToVisit(cityRequest.getRecommendToVisit())
                .notRecommendToVisit(cityRequest.getNotRecommendToVisit())
                .country(country)
                .build();

        cityRepository.save(newCity);
        return cityMapper.entityToDto(newCity);
    }

    @Transactional
    public void update(UpdateCityRequest updateCityRequest) {
        cityRepository.findById(updateCityRequest.getId()).ifPresent(city ->
                cityRepository.updateCty(city.getId(), updateCityRequest.getDescription(), updateCityRequest.getName(),
                        updateCityRequest.getRecommendToVisit(), updateCityRequest.getNotRecommendToVisit()));
    }

    public void delete(Long id) {
        cityRepository.deleteById(id);
    }

    private Country getCountryByName(String name) {
        return countryRepository.findByName(name)
                .orElse(countryRepository.save(Country.builder().name(name).build()));
    }
}
