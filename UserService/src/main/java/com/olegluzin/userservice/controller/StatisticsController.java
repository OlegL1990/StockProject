package com.olegluzin.userservice.controller;

import com.olegluzin.userservice.model.User;
import com.olegluzin.userservice.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/totalvalue/{id}")
    public Double totalPortfolioValue(@PathVariable String id) {
        return statisticsService.getTotalPortfolioValue(id);
    }
}
