package com.rezende.learn.repositories;

import com.rezende.learn.entities.Category;
import com.rezende.learn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
