package com.example.icesitrade.service;

import com.example.icesitrade.model.History;

import java.util.List;

public interface HistoryService {
    History saveHistory(History history);
    List<History> getUserHistory(Long userId);
}
