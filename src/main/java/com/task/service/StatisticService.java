package com.task.service;

import com.task.mapper.StatisticMapper;
import com.task.model.Country;
import com.task.model.Statistic;
import com.task.model.dto.CreateStatisticRequest;
import com.task.model.dto.StatisticResponse;
import com.task.model.dto.UpdateStatisticRequest;
import com.task.repository.CountryRepository;
import com.task.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository statisticRepository;
    private final CountryRepository countryRepository;

    private final StatisticMapper statisticMapper;

    public List<StatisticResponse> getAll() {
        return statisticRepository.findAll().stream()
                .map(statisticMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public StatisticResponse create(CreateStatisticRequest createStatisticRequest) {
        Country country = getByCountryName(createStatisticRequest.getCountry());

        Statistic statistic = Statistic.builder()
                .amount(createStatisticRequest.getAmount())
                .day(createStatisticRequest.getDay())
                .isQuarantineNeeded(createStatisticRequest.getIsQuarantineNeeded())
                .country(country)
                .build();

        statisticRepository.save(statistic);
        return statisticMapper.entityToDto(statistic);
    }

    @Transactional
    public void update(UpdateStatisticRequest updateStatisticRequest) {
        statisticRepository.findById(updateStatisticRequest.getId()).ifPresent(statistic ->
                statisticRepository.updateStatistic(statistic.getId(), updateStatisticRequest.getAmount(),
                        updateStatisticRequest.getDay(), updateStatisticRequest.getIsQuarantineNeeded()));
    }

    public void delete(Long id) {
        statisticRepository.deleteById(id);
    }

    private Country getByCountryName(String name) {
        return countryRepository.findByName(name)
                .orElseGet(() -> countryRepository.save(Country.builder().name(name).build()));
    }
}
