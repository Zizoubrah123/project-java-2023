package com.java.projectJwt.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.projectJwt.models.Favorite;



public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

}
