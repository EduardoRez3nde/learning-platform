-- src/main/resources/db/migration/V1__create_table_category.sql
CREATE TABLE tb_category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    position INTEGER NOT NULL
);
