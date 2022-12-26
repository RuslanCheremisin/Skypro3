package com.example.skypro3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping("/")
    public String launchApp() {
        return "Приложение запущено";
    }
    @GetMapping("/info")
    public String info() {
        return "<pre>Черемисин Руслан. Web-приложение для сайта рецептов. <pre/>" +
                "<pre>С помощью этого приложения вы сможете выбрать<pre/>" +
                "<pre>понравившийся вам рецепт (у нас самый подробный каталог<pre/>" +
                "<pre>блюд со всего света) и точно рассчитать <pre/>" +
                "<pre>количество ингридиентов на необходимое количество порций.<pre/>" +
                "<pre>Старт разработки 09.12.2022.<pre/>" +
                "<pre>Приложение разрабатывается на Java 17 с помощью библиотеки Spring" +
                " и сборщика приложений Maven.<pre/>";
    }
}
