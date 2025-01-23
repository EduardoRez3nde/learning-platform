package com.rezende.learn.repositories;

import com.rezende.learn.entities.Like;
import com.rezende.learn.entities.LikePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikePK> {
}
