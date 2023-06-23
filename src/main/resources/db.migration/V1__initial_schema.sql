CREATE SCHEMA cutlink;

CREATE TABLE links (
    user_url TEXT PRIMARY KEY,
    new_url TEXT NOT NULL UNIQUE
);

INSERT INTO links (user_url, new_url)
VALUES ('https://google.com', 'https://go');

SELECT user_url,
       new_url
FROM links
WHERE user_url = ?;
