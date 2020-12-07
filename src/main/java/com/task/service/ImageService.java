package com.task.service;

import com.task.exception.ObjectNotFoundException;
import com.task.mapper.ImageMapper;
import com.task.model.City;
import com.task.model.Image;
import com.task.model.dto.CreateImageRequest;
import com.task.model.dto.ImageResponse;
import com.task.repository.CityRepository;
import com.task.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ImageService.
 *
 * @author Viktoryia Zhak
 */
@RequiredArgsConstructor
@Service
public class ImageService {
    private final CityRepository cityRepository;
    private final ImageRepository imageRepository;

    private final ImageMapper imageMapper;

    public List<ImageResponse> getAll() {
        return imageRepository.findAll().stream()
                .sorted(Comparator.comparing(image -> image.getCity().getName()))
                .map(imageMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public ImageResponse create(CreateImageRequest createImageRequest) {
        City city = getCityByName(createImageRequest.getCity());

        Image image = Image.builder()
                .city(city)
                .url(createImageRequest.getUrl())
                .isVideo(createImageRequest.getIsVideo())
                .build();

        imageRepository.save(image);
        return imageMapper.entityToDto(image);
    }

    public void delete(Long id) {
        imageRepository.deleteById(id);
    }


    public List<ImageResponse> getImagesByCity(String name) {
        return imageRepository.findByCityName(name).stream()
                .map(imageMapper::entityToDto)
                .collect(Collectors.toList());
    }

    private City getCityByName(String name) {
        return cityRepository.findByName(name)
                .orElseThrow(() -> new ObjectNotFoundException(String.format("City %s doesnt't exist", name)));
    }
}
