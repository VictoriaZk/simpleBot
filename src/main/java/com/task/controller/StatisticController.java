package com.task.controller;

import com.task.model.dto.*;
import com.task.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/statistics")
public class StatisticController {
    private final StatisticService statisticService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("statistics", statisticService.getAll());
        return "statistic";
    }

    @PostMapping("/add")
    public String create(CreateStatisticRequest createStatisticRequest) {
        statisticService.create(createStatisticRequest);
        return "redirect:/statistics";
    }

    @PostMapping("/update/{id}")
    public String update(UpdateStatisticRequest updateStatisticRequest) {
        statisticService.update(updateStatisticRequest);
        return "redirect:/statistics";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        statisticService.delete(id);
        return "redirect:/statistics";
    }
}
