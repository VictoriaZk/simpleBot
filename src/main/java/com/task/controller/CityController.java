package com.task.controller;

import com.task.model.dto.CreateCityRequest;
import com.task.model.dto.CityResponse;
import com.task.model.dto.UpdateCityRequest;
import com.task.service.impl.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/cities")
public class CityController {
    private final CityService cityService;

    @PostMapping
    public ResponseEntity<CityResponse> create(@RequestBody CreateCityRequest cityRequest) {
        return ResponseEntity.ok(cityService.create(cityRequest));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UpdateCityRequest updateCityRequest) {
        cityService.update(updateCityRequest);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
