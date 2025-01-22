package com.rezende.learn.repositories;

import com.rezende.learn.entities.Favorite;
import com.rezende.learn.entities.FavoritePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, FavoritePK> {
}
