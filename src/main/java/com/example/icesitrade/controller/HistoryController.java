package com.example.icesitrade.controller;

import com.example.icesitrade.model.History;
import com.example.icesitrade.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping("/user/{userId}")
    public List<History> getUserHistory(@PathVariable Long userId) {
        return historyService.getUserHistory(userId);
    }

    @PostMapping
    public History saveHistory(@RequestBody History history) {
        return historyService.saveHistory(history);
    }
}
