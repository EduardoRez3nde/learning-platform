package com.rezende.learn.repositories;

import com.rezende.learn.entities.Category;
import com.rezende.learn.entities.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends JpaRepository<Episode, Long> {
}
