package com.example.demo.api;

import com.example.demo.utils.EndPoint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(EndPoint.EXAMPLE)
@RequiredArgsConstructor
@Tag(name = "Общая информация")
public class ExampleController {
    @GetMapping
    @Operation(summary = "Доступно всем ролям.")
    public String example() {
        return "Hello, world!";
    }
}
