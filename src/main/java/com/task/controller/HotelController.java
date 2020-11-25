package com.task.controller;

import com.task.model.dto.CreateHotelRequest;
import com.task.model.dto.HotelResponse;
import com.task.model.dto.UpdateCountryRequest;
import com.task.model.dto.UpdateHotelRequest;
import com.task.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/hotels")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("hotels", hotelService.getAll());
        return "hotel";
    }

    @PostMapping("/add")
    public String create(CreateHotelRequest hotelRequest) {
        hotelService.create(hotelRequest);
        return "redirect:/hotels";
    }

    @PostMapping("/update/{id}")
    public String update(UpdateHotelRequest hotelRequest) {
        hotelService.update(hotelRequest);
        return "redirect:/hotels";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        hotelService.delete(id);
        return "redirect:/hotels";
    }
}
