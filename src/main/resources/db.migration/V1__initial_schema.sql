CREATE SCHEMA cutlink;

CREATE TABLE user_link (
    id BIGSERIAL PRIMARY KEY,
    url TEXT NOT NULL UNIQUE
);

CREATE TABLE generate_link (
    id BIGSERIAL PRIMARY KEY,
    generate_url TEXT NOT NULL UNIQUE,
    user_link_id INT REFERENCES user_link(id) ON DELETE CASCADE
);