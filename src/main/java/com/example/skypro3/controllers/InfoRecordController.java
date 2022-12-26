package com.example.skypro3.controllers;

import com.example.skypro3.record.InfoRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class InfoRecordController {
    @GetMapping("/")
    public String launchApp() {
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public InfoRecord info() {
        return new InfoRecord("Черемисин Руслан", "Recipe Book", LocalDate.of(2022, 12, 24), "test");
    }
}
