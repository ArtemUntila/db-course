INSERT INTO author (title, country)
VALUES ('Rammstein', 'Germany'), ('Dream Theater', 'USA'), ('Charlie Clouser', 'USA');

INSERT INTO genre (title)
VALUES ('Industrial'), ('Industrial metal'), ('Progressive metal'), ('Progressive rock'), ('Electronic');

INSERT INTO author_genre (author_id, genre_id)
VALUES (1, 1), (1, 2), (3, 2), (3, 5);

CALL insert_author_genre('Dream Theater', 'Progressive metal');
CALL insert_author_genre('Dream Theater', 'Progressive rock');

INSERT INTO album (type, title, release, author_id)
VALUES ('album', 'A View From The Top Of The World', '2021-10-22', 2),
       ('album', 'Remixes', '2020-03-27', 1),
       ('single', 'Deutschland', '2019-03-28', 1);

INSERT INTO album_genre (album_id, genre_id)
VALUES (1, 3), (2, 1), (3, 2);

INSERT INTO track (title, album_id, duration, lyrics)
VALUES ('Stripped', 2, '00:05:12', 'Let me see you stripped down to the bone'),
       ('Deutschland', 3, '00:05:22', Null);

INSERT INTO track (title, album_id, duration)
VALUES ('The Alien', 1, '00:09:31'),
       ('Answering the Call', 1, '00:07:35'),
       ('Invisible Monster', 1, '00:06:30'),
       ('Sleeping Giant', 1, '00:10:04'),
       ('Transcending Time', 1, '00:06:24'),
       ('Awaken the Master', 1, '00:09:47'),
       ('A View from the Top of the World', 1, '00:20:23');

INSERT INTO author_track (author_id, track_id, type)
VALUES (1, 1, 'main'), (3, 1, 'remix'), (1, 2, 'main');

INSERT INTO author_track (author_id, track_id, type)
VALUES (2, 3, 'main'), (2, 4, 'main'), (2, 5, 'main'), (2, 6, 'main'), (2, 7, 'main'), (2, 8, 'main'), (2, 9, 'main');

-- Individual Task 1.2

INSERT INTO "user" ("name") VALUES ('untila.aa'), ('zhemelev.ga');

INSERT INTO playlist (title, user_id) VALUES ('favourites', 1), ('study', 1), ('favourites', 2), ('work', 2);

INSERT INTO playlist_track (playlist_id, track_id) VALUES (1, 3), (1, 9), (2, 1), (2, 6), (2, 7), (3, 1), (3, 2), (4, 4), (4, 8);