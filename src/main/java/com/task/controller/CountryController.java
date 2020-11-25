package com.task.controller;

import com.task.model.dto.UpdateCountryRequest;
import com.task.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/countries")
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("countries", countryService.getAll());
        return "country";
    }

    @PostMapping("/add")
    public String create(@RequestParam String name) {
        countryService.create(name);
        return "redirect:/countries";
    }

    @PostMapping("/update/{id}")
    public String update(UpdateCountryRequest countryRequest) {
        countryService.update(countryRequest);
        return "redirect:/countries";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        countryService.delete(id);
        return "redirect:/countries";
    }
}
