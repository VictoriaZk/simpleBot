package com.task.service;

import com.task.exception.ObjectNotFoundException;
import com.task.mapper.HotelMapper;
import com.task.model.City;
import com.task.model.Hotel;
import com.task.model.dto.CreateHotelRequest;
import com.task.model.dto.HotelResponse;
import com.task.model.dto.UpdateHotelRequest;
import com.task.repository.CityRepository;
import com.task.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HotelService {
    private final HotelRepository hotelRepository;
    private final CityRepository cityRepository;

    private final HotelMapper hotelMapper;

    public List<HotelResponse> getAll() {
        return hotelRepository.findAll().stream()
                .map(hotelMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public HotelResponse create(CreateHotelRequest hotelRequest) {
        City city = getCityByName(hotelRequest.getCity());

        Hotel hotel = Hotel.builder()
                .amountOfStars(hotelRequest.getStars())
                .name(hotelRequest.getName())
                .cost(hotelRequest.getCost())
                .city(city)
                .build();

        hotelRepository.save(hotel);
        return hotelMapper.entityToDto(hotel);
    }

    @Transactional
    public void update(UpdateHotelRequest updateHotelRequest) {
        hotelRepository.findById(updateHotelRequest.getId()).ifPresent(hotel ->
                hotelRepository.updateNameAndStars(hotel.getId(), updateHotelRequest.getName(),
                        updateHotelRequest.getStars(), updateHotelRequest.getCost()));
    }

    public void delete(Long id) {
        hotelRepository.deleteById(id);
    }

    private City getCityByName(String name) {
        return cityRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("City %s doesnt't exist", name)));
    }
}
