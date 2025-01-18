package com.rezende.learn.repositories;

import com.rezende.learn.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM tb_course WHERE featured = true")
    List<Course> findFeaturedCourses();

    @Query(nativeQuery = true, value = "SELECT * FROM tb_course ORDER BY created_at DESC")
    List<Course> findRecentCourses();

    @Query(nativeQuery = true, value = "SELECT * FROM tb_course WHERE tb_course.name LIKE %:name%")
    Page<Course> findByName(@Param("name") String name, Pageable pageable);

    @Query(nativeQuery = true, value =
            "SELECT tb_course.id, tb_course.name, tb_course.synopsis, tb_course.thumbnail_url, " +
                    "tb_course.featured, tb_course.category_id, tb_course.created_at, tb_course.updated_at, " +
                    "COUNT(user_id) AS likes " +
                    "FROM tb_course " +
                    "LEFT JOIN ( " +
                    "    SELECT tb_like.course_id, tb_users.id AS user_id " +
                    "    FROM tb_like " +
                    "    INNER JOIN tb_users ON tb_users.id = tb_like.user_id " +
                    ") AS likes_users ON tb_course.id = likes_users.course_id " +
                    "GROUP BY tb_course.id " +
                    "ORDER BY likes DESC " +
                    "LIMIT 10")
    List<Course> findTopByLikes();
}
