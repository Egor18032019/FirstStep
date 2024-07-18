package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Info {
    String info;
    LocalDate date = LocalDate.now();

    public Info(String info) {
        this.info = info;
    }
}
