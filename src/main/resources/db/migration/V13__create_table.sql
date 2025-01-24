CREATE EXTENSION IF NOT EXISTS pgcrypto;

TRUNCATE TABLE tb_users RESTART IDENTITY CASCADE;

ALTER TABLE tb_users
ADD COLUMN password VARCHAR(255);

UPDATE tb_users
SET password = crypt('default_password', gen_salt('bf'));


CREATE TABLE tb_role (
    id SERIAL PRIMARY KEY,
    authority VARCHAR NOT NULL
);

CREATE TABLE tb_user_role (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES tb_users(id),
    FOREIGN KEY (role_id) REFERENCES tb_role(id)
);
-- V2__Insert_roles.sql

INSERT INTO tb_role (authority)
VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN');

-- V3__Insert_users.sql

INSERT INTO tb_users (firstname, lastname, phone, birth_date, email, password, created_at, updated_at)
VALUES
    ('Alice', 'Silva', '555-1234', '1995-05-15', 'alice.silva@example.com', crypt('password1', gen_salt('bf')), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Bob', 'Souza', '555-5678', '1990-03-10', 'bob.souza@example.com', crypt('password2', gen_salt('bf')), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Carol', 'Lima', '555-9012', '1988-07-22', 'carol.lima@example.com', crypt('password3', gen_salt('bf')), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Daniel', 'Oliveira', '555-3456', '1993-09-08', 'daniel.oliveira@example.com', crypt('password4', gen_salt('bf')), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Eve', 'Costa', '555-7890', '2000-12-05', 'eve.costa@example.com', crypt('password5', gen_salt('bf')), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- V4__Insert_user_roles.sql

INSERT INTO tb_user_role (user_id, role_id)
VALUES
    ((SELECT id FROM tb_users WHERE email='alice.silva@example.com'), (SELECT id FROM tb_role WHERE authority='ROLE_USER')),
    ((SELECT id FROM tb_users WHERE email='bob.souza@example.com'), (SELECT id FROM tb_role WHERE authority='ROLE_USER')),
    ((SELECT id FROM tb_users WHERE email='carol.lima@example.com'), (SELECT id FROM tb_role WHERE authority='ROLE_USER')),
    ((SELECT id FROM tb_users WHERE email='daniel.oliveira@example.com'), (SELECT id FROM tb_role WHERE authority='ROLE_ADMIN')),
    ((SELECT id FROM tb_users WHERE email='eve.costa@example.com'), (SELECT id FROM tb_role WHERE authority='ROLE_ADMIN'));
