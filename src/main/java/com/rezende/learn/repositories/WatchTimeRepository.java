package com.rezende.learn.repositories;

import com.rezende.learn.entities.Category;
import com.rezende.learn.entities.WatchTime;
import com.rezende.learn.entities.WatchTimePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchTimeRepository extends JpaRepository<WatchTime, WatchTimePK> {
}
