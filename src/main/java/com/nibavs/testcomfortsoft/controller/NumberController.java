package com.nibavs.testcomfortsoft.controller;

import com.nibavs.testcomfortsoft.service.NumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Удобный софт", description = "Тестовый контроллер")
public class NumberController {

    private final NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }

    @Operation(summary = "Получить N-ное минимальное число из файла",
            description = "Задавать путь и n нужно через query параметры. Пример запроса http://localhost:8080/min?path=input.xlsx&n=4")
    @GetMapping("/min")
    public int getNthMinInt(
            @Parameter(
                    name = "path",
                    description = "Путь к локальному XLSX-файлу. "
                            + "Пример: /Users/nibavs/Downloads/input.xlsx",
                    example = "input.xlsx"
            )
            @RequestParam String path,

            @Parameter(
                    name = "n",
                    description = "Число n",
                    example = "4"
            )
            @RequestParam int n) throws Exception {
        return numberService.findNthSmallest(path, n);
    }

}
