package com.example.demodbbootclient.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
public class Register {
    private long id;

    // Номер рейса
    private String numFlight;
    // Маршрут
    private String trip;

    private String stopoverPoints;     // Пункты промежуточной посадки

    private String timeFlight;     // Время отправления

    private String dayFlight;     // Дни отправления

    private String AvailabilitySeatsFlight;     // Количество свободных мест на каждом рейсе
}
