CREATE TABLE tb_favorite (
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    updated_at TIMESTAMP WITHOUT TIME ZONE,
    PRIMARY KEY (user_id, course_id),
    CONSTRAINT fk_user
        FOREIGN KEY (user_id)
        REFERENCES tb_users (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_course
        FOREIGN KEY (course_id)
        REFERENCES tb_course (id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Inserir dados na tabela tb_favorite
INSERT INTO tb_favorite (user_id, course_id, created_at, updated_at)
VALUES
    (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Alice favoritou "Introdução à Programação"
    (1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Alice favoritou "Desenvolvimento Web com HTML, CSS e JavaScript"
    (2, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Bob favoritou "Java para Iniciantes"
    (3, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Carol favoritou "Python para Data Science"
    (4, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);  -- Daniel favoritou "Desenvolvimento de Aplicativos Android"
