package com.task.controller;

import com.task.model.dto.CreateHotelRequest;
import com.task.model.dto.CreateImageRequest;
import com.task.model.dto.UpdateHotelRequest;
import com.task.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * ImageController.
 *
 * @author Viktoryia Zhak
 */
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/photos")
public class ImageController {
    private final ImageService imageService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("photos", imageService.getAll());
        return "photo";
    }

    @PostMapping("/add")
    public String create(@Valid CreateImageRequest imageRequest) {
        imageService.create(imageRequest);
        return "redirect:/photos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        imageService.delete(id);
        return "redirect:/photos";
    }
}
