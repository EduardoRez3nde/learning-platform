package com.rezende.learn.repositories;

import com.rezende.learn.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long>{

    @Query(nativeQuery = true, value=
        """
        SELECT
            tb_course.id AS course_id, 
            tb_course.name AS course_name, 
            tb_course.synopsis AS course_synopsis, 
            tb_course.thumbnail_url AS course_thumbnail_url, 
            tb_episode.id AS episode_id, 
            tb_episode.name AS episode_name, 
            tb_episode.synopsis AS episode_synopsis, 
            tb_episode.episode_order AS episode_order, 
            tb_episode.video_url AS episode_video_url, 
            tb_episode.seconds_long AS episode_seconds_long 
          FROM tb_course 
          INNER JOIN tb_episode ON tb_course.id = tb_episode.course_id 
        """
    )
    Optional<Course> findByIdWithEpisodes(@Param("id") Long id);
}
