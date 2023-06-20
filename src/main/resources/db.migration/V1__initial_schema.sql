CREATE TABLE user_link (
    id BIGSERIAL PRIMARY KEY,
    url TEXT NOT NULL UNIQUE
);

CREATE TABLE generate_link (
    id BIGSERIAL PRIMARY KEY,
    url TEXT NOT NULL UNIQUE,
    user_link_id INT REFERENCES user_link(id) ON DELETE CASCADE
);

INSERT INTO user_link(url)
VALUES ('https://google.com');

INSERT INTO generate_link (url, user_link_id)
VALUES ('test', 1);

SELECT id,
       url,
       user_link_id
FROM generate_link
WHERE user_link_id = ?;

SELECT generate_link.id,
       generate_link.url,
       user_link_id
FROM generate_link
JOIN user_link ul on generate_link.user_link_id = ul.id
WHERE ul.url = 'https://google.com';
