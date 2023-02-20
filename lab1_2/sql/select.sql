SELECT album.title as album, type, author.title as author, genre.title as genre, country, release
FROM album
    JOIN author USING (author_id)
    JOIN album_genre USING (album_id)
    JOIN genre USING (genre_id);

SELECT track.title as track, author.title as author, album.title as album, duration
FROM track
    JOIN author_track ON ((track.track_id = author_track.track_id) AND (type = 'main'))
    JOIN author USING (author_id)
    JOIN album USING (album_id);

-- Individual Task 1.2
SELECT "name" as username, playlist.title as playlist, track.title as track
FROM "user"
    JOIN playlist USING (user_id)
    JOIN playlist_track USING (playlist_id)
    JOIN track USING (track_id)
