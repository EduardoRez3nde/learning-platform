package com.rezende.learn.repositories;

import com.rezende.learn.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Long, Course>{
}
