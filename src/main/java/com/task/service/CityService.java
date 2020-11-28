package com.task.service;

import com.task.mapper.CityMapper;
import com.task.model.City;
import com.task.model.Country;
import com.task.model.FutureCity;
import com.task.model.Message;
import com.task.model.Statistic;
import com.task.model.dto.CityResponse;
import com.task.model.dto.CreateCityRequest;
import com.task.model.dto.UpdateCityRequest;
import com.task.repository.CityRepository;
import com.task.repository.CountryRepository;
import com.task.repository.FutureCityRepository;
import com.task.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final StatisticRepository statisticRepository;
    private final FutureCityRepository futureCityRepository;
    private final CityMapper cityMapper;

    public String getInformation(String cityName) {
        City city = cityRepository.findByName(cityName).orElse(null);
        if (Objects.nonNull(city)) {
            return getMessage(city);
        } else {
            addToFutureCities(cityName);
            return Message.APOLOGIZE_MESSAGE.getApologizeMessage();
        }
    }

    public List<CityResponse> getAll() {
        return cityRepository.findAll().stream()
                .map(cityMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public CityResponse create(CreateCityRequest cityRequest) {
        Country country = getCountryByName(cityRequest.getCountry());

        checkingInFutureCities(cityRequest.getName());

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
                .orElseGet(() -> countryRepository.save(Country.builder().name(name).build()));
    }

    private String getMessage(City city) {
        String country = "";
        String amount = "";
        String quarantine = "";
        String cityDescription = city.getDescription();
        String recommend = "Некоторые факты и рекомендации: " + city.getRecommendToVisit();
        String notRecommend = "Также мы собрали негативные отзывы о некоторых местах: " + city.getNotRecommendToVisit();
        List<String> hotels = new ArrayList<>();

        city.getHotels().forEach(hotel -> {
            hotels.add("Отель:" + hotel.getName() + "," + "количество звезд: " + hotel.getAmountOfStars());
        });

        Optional<Statistic> statisticOptional = statisticRepository.findByCountryId(city.getCountry().getId());

        if (statisticOptional.isPresent()) {
            Statistic statistic = statisticOptional.get();
            country = "Город " + city.getName() + "принадлежит стране " + statistic.getCountry().getName();
            amount = "На данный момент количество зараженных в стране " + statistic.getAmount().toString();
            quarantine = "По приезду вам " + ((statistic.getIsQuarantineNeeded()) ? "необходимо будет соблюдать 14-дневный карантин."
                    : "не нужно отбывать карантин, но все же помните о своем здоровье и здоровье окружающих, носите маску!");
        }

        //отели сделать цену за сутки и сделать через запятую
        //в статистике добавить кол-во зараженных за сутки
        return city.getName() + "/n" + cityDescription + "/n" + recommend + "/n" + notRecommend + "/n" +
                "Список отелей, в которых вы можете остановиться" + hotels + "/n" +
                country + ". " + amount + ". " + quarantine + ". " ;
    }

    @Transactional
    public void addToFutureCities(String name) {
        Optional<FutureCity> city = futureCityRepository.findByName(name);
        if (city.isPresent()) {
            Integer amountOfRequests = city.get().getAmountOfRequests();
            city.get().setAmountOfRequests(++amountOfRequests);
            futureCityRepository.save(city.get());
        } else {
            futureCityRepository.save(FutureCity.builder()
                    .name(name)
                    .amountOfRequests(1)
                    .build());
        }
    }

    private void checkingInFutureCities(String name) {
        futureCityRepository.findByName(name).ifPresent(futureCityRepository::delete);
    }
}
