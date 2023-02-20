CREATE PROCEDURE insert_author_genre (author_title TEXT, genre_title TEXT)
LANGUAGE SQL AS $$
INSERT INTO author_genre (author_id, genre_id)
SELECT tmp.author_id, tmp.genre_id
FROM (
    SELECT author.author_id, genre.genre_id
    FROM author
        JOIN genre ON ((author.title = author_title) AND (genre.title = genre_title))
) as tmp
$$;
