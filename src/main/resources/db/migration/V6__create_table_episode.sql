CREATE TABLE tb_episode (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    synopsis TEXT NOT NULL,
    episode_order INT NOT NULL,
    video_url VARCHAR(255) NOT NULL,
    seconds_long BIGINT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    course_id BIGINT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES tb_course(id)
);