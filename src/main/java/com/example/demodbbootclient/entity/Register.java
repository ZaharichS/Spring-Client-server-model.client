package com.example.demodbbootclient.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Register {
    private Integer id;

    // Номер рейса
    private String numFlight;
    // Маршрут
    private String trip;

    private String stopoverPoints;     // Пункты промежуточной посадки

    private String timeFlight;     // Время отправления

    private String dayFlight;     // Дни отправления

    private String availabilitySeatsFlight;     // Количество свободных мест на каждом рейсе
}
