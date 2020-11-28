package com.task.controller;

import com.task.model.dto.CityResponse;
import com.task.model.dto.CreateCityRequest;
import com.task.model.dto.UpdateCityRequest;
import com.task.model.dto.UpdateCountryRequest;
import com.task.service.CityService;
import com.task.service.FutureCityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/cities")
public class CityController {
    private final FutureCityService futureCityService;
    private final CityService cityService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("cities", cityService.getAll());
        model.addAttribute("plans", futureCityService.getAll());
        return "city";
    }

    @PostMapping("/add")
    public String create(CreateCityRequest cityRequest) {
        cityService.create(cityRequest);
        return "redirect:/cities";
    }

    @PostMapping("/update/{id}")
    public String update(UpdateCityRequest updateCityRequest) {
        cityService.update(updateCityRequest);
        return "redirect:/cities";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        cityService.delete(id);
        return "redirect:/cities";
    }
}
