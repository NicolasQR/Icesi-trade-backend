package com.example.icesitrade.service.impl;

import com.example.icesitrade.model.History;
import com.example.icesitrade.repository.HistoryRepository;
import com.example.icesitrade.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    @Override
    public List<History> getUserHistory(Long userId) {
        return historyRepository.findByUserId(userId);
    }
}
