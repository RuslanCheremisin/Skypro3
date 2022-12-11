package com.example.skypro3;

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
        return "Черемисин Руслан. Web-приложение для сайта рецептов. " +
                "С помощью этого приложения вы сможете выбрать" +
                "понравившийся вам рецепт (у нас самый подробный каталог" +
                "блюд со всего света) и точно рассчитать " +
                "количество ингридиентов на необходимое количество порций." +
                "Старт разработки 09.12.2022." +
                "Приложение разрабатывается на Java 17 с помощью библиотеки Spring" +
                " и сборщика приложений Maven.";
    }
}
