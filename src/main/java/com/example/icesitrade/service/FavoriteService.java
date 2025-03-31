package com.example.icesitrade.service;

import com.example.icesitrade.model.Favorite;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {
    List<Favorite> getAllFavorites();
    Optional<Favorite> getFavoriteById(Long id);
    Favorite createFavorite(Favorite favorite);
    void deleteFavorite(Long id);
}
