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
import com.vdurmont.emoji.EmojiParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
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
        String recommend = "Что стоит посетить:" + EmojiParser.parseToUnicode(":statue_of_liberty:") +
                EmojiParser.parseToUnicode(":ferris_wheel:") + EmojiParser.parseToUnicode(":european_castle:") +
                EmojiParser.parseToUnicode(":violin:") + "\n " + city.getRecommendToVisit();
        String notRecommend = "Не стоит тратить время на посещение:" + EmojiParser.parseToUnicode(":x:")  +
                EmojiParser.parseToUnicode(":x:")  + EmojiParser.parseToUnicode(":x:")  +
                "\n " + city.getNotRecommendToVisit();
        List<String> hotels = new ArrayList<>();

        city.getHotels().forEach(hotel -> {
            hotels.add("\n" + EmojiParser.parseToUnicode(":bed:") + "Отель:" + hotel.getName() + "\n"
                    + EmojiParser.parseToUnicode(":star:") + "Количество звезд: " + hotel.getAmountOfStars() + "\n"
                    + EmojiParser.parseToUnicode(":dollar:") + "Цена за сутки:" + hotel.getCost() + "\n"
                    + EmojiParser.parseToUnicode(":white_check_mark:") + "Рейтинг:" + hotel.getRating());
        });

        Optional<Statistic> statisticOptional = statisticRepository.findByCountryId(city.getCountry().getId());

        if (statisticOptional.isPresent()) {
            Statistic statistic = statisticOptional.get();
            country = "\nГород " + city.getName() + " принадлежит стране " + statistic.getCountry().getName();
            amount = "На данный момент количество зараженных в стране " + EmojiParser.parseToUnicode("\ud83e\udda0") + statistic.getAmount().toString();
            quarantine = "\nПо приезду вам " + ((statistic.getIsQuarantineNeeded()) ? "необходимо будет соблюдать 14-дневный карантин." + EmojiParser.parseToUnicode(":pill:")
                    : "не нужно отбывать карантин, но все же помните о своем здоровье и здоровье окружающих, носите маску!" + EmojiParser.parseToUnicode(":mask:"));
        }

        //отели сделать цену за сутки и сделать через запятую
        //в статистике добавить кол-во зараженных за сутки
        return EmojiParser.parseToUnicode("\u2708\ufe0f") + city.getName() +
                "\n\n" + cityDescription +
                "\n\n" + recommend +
                "\n\n" + notRecommend + "\n" +
                "\nСписок отелей, в которых вы можете остановиться" + EmojiParser.parseToUnicode(":hotel:") + ":\n" +
                hotels.stream().collect(Collectors.joining("\n"))  + "\n\n" +
                country + ". " + amount + ". " + quarantine;
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
