-- Task 1
SELECT genre.title as genre, COUNT(track) as count
FROM playlist
    JOIN playlist_track USING (playlist_id)
    JOIN track USING (track_id)
    JOIN album USING (album_id)
    JOIN album_genre USING (album_id)
    JOIN genre USING (genre_id)
GROUP BY genre
ORDER BY count DESC
LIMIT 5;

-- Task 2
CREATE OR REPLACE FUNCTION get_albums_stat(author_id INT, OUT album_title TEXT, OUT duration_in_minutes INT, OUT genres TEXT) RETURNS SETOF RECORD AS $$
    SELECT album.title, floor(EXTRACT(EPOCH FROM SUM(track.duration)) / 60) as duration_in_minutes, STRING_AGG(DISTINCT genre.title, ', ') as genres
    FROM album
        JOIN track USING (album_id)
        JOIN album_genre USING (album_id)
        JOIN genre USING (genre_id)
    WHERE album.author_id = $1
    GROUP BY album.title;
$$ LANGUAGE SQL;

SELECT * FROM get_albums_stat(1);

-- Task 3
CREATE OR REPLACE FUNCTION get_main_authors(track_id INT) RETURNS TEXT AS $$
    SELECT string_agg(author.title, ', ')
    FROM author
        JOIN author_track ON (author.author_id = author_track.author_id) AND (author_track.type = 'main')
        JOIN track USING (track_id)
    WHERE track.track_id = $1
    GROUP BY track.title;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_feat_authors(track_id INT) RETURNS TEXT AS $$
    SELECT concat(' (', 'feat. ', string_agg(author.title, ', '), ')')
    FROM author
        JOIN author_track ON (author.author_id = author_track.author_id) AND (author_track.type = 'feat')
        JOIN track USING (track_id)
    WHERE track.track_id = $1
    GROUP BY track.title;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_remix_authors(track_id INT) RETURNS TEXT AS $$
    SELECT concat(' [', string_agg(author.title, ', '), ' Remix', ']')
    FROM author
        JOIN author_track ON (author.author_id = author_track.author_id) AND (author_track.type = 'remix')
        JOIN track USING (track_id)
    WHERE track.track_id = $1
    GROUP BY track.title;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_track_canonical_title(track_id INT) RETURNS TEXT AS $$
    SELECT CONCAT(
        get_main_authors(track.track_id), ' — ', track.title,
        get_feat_authors(track.track_id),
        get_remix_authors(track.track_id)
    )
    FROM track
    WHERE track.track_id = $1;
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_playlists_pretty_jsonb(user_id INT) RETURNS TEXT AS $$
SELECT jsonb_pretty(json_agg(t)::jsonb)
FROM (
    SELECT playlist.title as playlist, json_agg(get_track_canonical_title(track_id)) as "track list"
    FROM playlist
        JOIN playlist_track USING (playlist_id)
        JOIN track USING (track_id)
    WHERE playlist.user_id = $1
    GROUP BY playlist
) as t;
$$ LANGUAGE SQL;

SELECT get_playlists_pretty_jsonb(10);