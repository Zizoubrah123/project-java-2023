package com.java.projectJwt.services;

public interface FavoriteService {
    void addProductToFavorites(Long userId, Long productId);
    void removeProductFromFavorites(Long favoriteId);
}