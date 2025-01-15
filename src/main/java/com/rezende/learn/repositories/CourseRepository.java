package com.rezende.learn.repositories;

import com.rezende.learn.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long>{

    @Query(nativeQuery = true, value=
        """
        SELECT * FROM tb_course
        WHERE featured = true;
        """)
    List<Course> findFeaturedCourses();
}
