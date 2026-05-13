package com.spring.school.controller;

import com.spring.school.model.Holiday;
import com.spring.school.service.HolidayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HolidayController {
    private final HolidayService holidayService;

    @RequestMapping("/holidays")
    public String displayHolidaysPage(
            @RequestParam(required = false, defaultValue = "true") boolean festival,
            @RequestParam(required = false, defaultValue = "true") boolean federal,
            Model model) {
        model.addAttribute("festival", festival);
        model.addAttribute("federal", federal);

        List<Holiday> holidays = holidayService.getAllHolidays();

        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    holidays.stream().filter(holiday -> holiday.getType().equals(type)).toList());
        }

        return "holidays";
    }
}
