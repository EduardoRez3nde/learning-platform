INSERT INTO tb_like (user_id, course_id, created_at, updated_at)
VALUES
    (1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Alice gostou do curso de Python
    (1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Alice gostou do curso de Estruturas de Dados em Python
    (2, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Bob gostou do curso de Java Básico
    (3, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Carol gostou do curso de Programação Funcional em Java
    (4, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Daniel gostou do curso de JavaScript Moderno
    (5, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Eve gostou do curso de Python
    (5, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); -- Eve gostou do curso de Java Básico

INSERT INTO tb_watch_time (user_id, episode_id, seconds, created_at, updated_at)
VALUES
    (1, 1, 1200, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Alice assistiu 20 minutos do episódio 1
    (2, 2, 600, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Bob assistiu 10 minutos do episódio 2
    (3, 3, 1800, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Carol assistiu 30 minutos do episódio 3
    (4, 4, 900, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),  -- Daniel assistiu 15 minutos do episódio 4
    (5, 5, 3600, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); -- Eve assistiu 1 hora do episódio 5
