INSERT INTO tb_category (name, position) VALUES ('Desenvolvimento', 1);
INSERT INTO tb_category (name, position) VALUES ('Design', 2);
INSERT INTO tb_category (name, position) VALUES ('Marketing', 3);

INSERT INTO tb_category (name, position) VALUES ('Desenvolvimento Web', 1);
INSERT INTO tb_category (name, position) VALUES ('Design Gráfico', 2);
INSERT INTO tb_category (name, position) VALUES ('Marketing Digital', 3);
INSERT INTO tb_category (name, position) VALUES ('Gestão de Projetos', 4);

INSERT INTO tb_course (name, synopsis, thumbnail_url, featured, category_id) VALUES ('JavaScript Moderno', 'Domine as técnicas mais recentes de JavaScript.', 'url_js', true, 1);
INSERT INTO tb_course (name, synopsis, thumbnail_url, featured, category_id) VALUES ('Design para Web', 'Crie interfaces web incríveis.', 'url_design', false, 2);
INSERT INTO tb_course (name, synopsis, thumbnail_url, featured, category_id) VALUES ('SEO para Iniciantes', 'Aprenda a otimizar seu site para os mecanismos de busca.', 'url_seo', true, 3);
INSERT INTO tb_course (name, synopsis, thumbnail_url, featured, category_id) VALUES ('Gerenciamento Ágil de Projetos', 'Domine os princípios do Agile e Scrum.', 'url_agile', false, 4);
INSERT INTO tb_course (name, synopsis, thumbnail_url, featured, category_id) VALUES ('React do Zero ao Avançado', 'Construa aplicações web robustas com React.', 'url_react', true, 1);

INSERT INTO tb_course (name, synopsis, thumbnail_url, featured, category_id) VALUES ('Dominando JavaScript', 'Aprenda JavaScript do zero e se torne um profissional de desenvolvimento web.', 'https://via.placeholder.com/150', true, 1);
INSERT INTO tb_course (name, synopsis, thumbnail_url, featured, category_id) VALUES ('Fundamentos de Design UI/UX', 'Domine o básico do design de interface e experiência do usuário.', 'https://via.placeholder.com/150', false, 2);
INSERT INTO tb_course (name, synopsis, thumbnail_url, featured, category_id) VALUES ('O Kit de Ferramentas de Marketing Definitivo', 'Aprenda estratégias essenciais de marketing para expandir seus negócios.', 'https://via.placeholder.com/150', true, 3);

INSERT INTO tb_episode (name, synopsis, episode_order, video_url, seconds_long, course_id) VALUES ('Introdução ao JavaScript', 'Conceitos básicos de JS.', 1, 'url_video1', 1200, 1);
INSERT INTO tb_episode (name, synopsis, episode_order, video_url, seconds_long, course_id) VALUES ('Variáveis e Tipos', 'Trabalhando com dados em JS.', 2, 'url_video2', 1500, 1);
INSERT INTO tb_episode (name, synopsis, episode_order, video_url, seconds_long, course_id) VALUES ('HTML e CSS para Designers', 'Estruturando e estilizando páginas web.', 1, 'url_video3', 1800, 2);
INSERT INTO tb_episode (name, synopsis, episode_order, video_url, seconds_long, course_id) VALUES ('O que é SEO?', 'Entendendo a otimização para buscadores.', 1, 'url_video4', 900, 3);
INSERT INTO tb_episode (name, synopsis, episode_order, video_url, seconds_long, course_id) VALUES ('Fundamentos do Scrum', 'Princípios e práticas do Scrum.', 1, 'url_video5', 2100, 4);
INSERT INTO tb_episode (name, synopsis, episode_order, video_url, seconds_long, course_id) VALUES ('Introdução ao React', 'Primeiros passos com React.', 1, 'url_video6', 1500, 5);
INSERT INTO tb_episode (name, synopsis, episode_order, video_url, seconds_long, course_id) VALUES ('Introdução ao JavaScript', 'O que é JavaScript e por que é importante?', 1, 'https://www.youtube.com/watch?v=fqB8vbLNvOs', 1800, 1);
INSERT INTO tb_episode (name, synopsis, episode_order, video_url, seconds_long, course_id) VALUES ('Variáveis e Tipos de Dados', 'Aprenda como armazenar e manipular dados em JavaScript.', 2, 'https://www.youtube.com/watch?v=8aGhZQdqRM0', 2400, 1);
INSERT INTO tb_episode (name, synopsis, episode_order, video_url, seconds_long, course_id) VALUES ('Instruções de Fluxo de Controle', 'Aprenda como controlar o fluxo de seus programas JavaScript.', 3, 'https://www.youtube.com/watch?v=Wq4tPdOKWBk', 1500, 1);

-- Adicione mais episódios, cursos e outras entidades conforme necessário
INSERT INTO tb_user (firstname, lastname, phone, birth_date, email) VALUES ('Ana', 'Pereira', '11977777777', '1995-03-10', 'ana.pereira@email.com');
INSERT INTO tb_user (firstname, lastname, phone, birth_date, email) VALUES ('Carlos', 'Rodrigues', '11966666666', '1988-11-25', 'carlos.rodrigues@email.com');
INSERT INTO tb_user (firstname, lastname, phone, birth_date, email) VALUES ('Mariana', 'Almeida', '11955555555', '2000-07-02', 'mariana.almeida@email.com');
INSERT INTO tb_user (firstname, lastname, phone, birth_date, email) VALUES ('João', 'Silva', '11999999999', '1990-01-15', 'joao.silva@email.com');
INSERT INTO tb_user (firstname, lastname, phone, birth_date, email) VALUES ('Maria', 'Santos', '11988888888', '1985-05-20', 'maria.santos@email.com');

-- Exemplo de inserção em tabelas com chave composta (Favorite e Like)


INSERT INTO tb_favorite (course_id, user_id) VALUES (1, 1); -- Curso 1, Usuário 1
INSERT INTO tb_favorite (course_id, user_id) VALUES (2, 1); -- Ana favorita Design para Web
INSERT INTO tb_favorite (course_id, user_id) VALUES (5, 2); -- Carlos favorita React do Zero ao Avançado

INSERT INTO tb_like (course_id, user_id) VALUES (1, 1); -- Ana curte JavaScript Moderno
INSERT INTO tb_like (course_id, user_id) VALUES (1, 2); -- Carlos curte JavaScript Moderno
INSERT INTO tb_like (course_id, user_id) VALUES (3, 3); -- Mariana curte SEO para Iniciantes
INSERT INTO tb_like (course_id, user_id) VALUES (2, 2);-- Curso 2, Usuário 2

-- Exemplo de inserção em WatchTime
INSERT INTO tb_watch_time (episode_id, user_id, seconds) VALUES (1, 1, 600); -- Episódio 1, Usuário 1, 600 segundos assistidos
INSERT INTO tb_watch_time (episode_id, user_id, seconds) VALUES (2, 1, 600); -- Ana assistiu 600 segundos do primeiro episódio de JavaScript Moderno
INSERT INTO tb_watch_time (episode_id, user_id, seconds) VALUES (2, 2, 900); -- Ana assistiu 900 segundos do segundo episódio de JavaScript Moderno
INSERT INTO tb_watch_time (episode_id, user_id, seconds) VALUES (4, 3, 450); -- Mariana assistiu 450 segundos do primeiro episódio de SEO para Iniciantes