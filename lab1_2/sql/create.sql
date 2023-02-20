CREATE TABLE IF NOT EXISTS author (
    author_id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    country TEXT NOT NULL
);

CREATE TYPE album_type AS ENUM ('album', 'single', 'EP');

CREATE TABLE IF NOT EXISTS album (
    album_id SERIAL PRIMARY KEY,
    "type" album_type NOT NULL,
    title TEXT NOT NULL,
    "release" DATE NOT NULL,
    author_id INT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author (author_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS track (
    track_id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    album_id INT NOT NULL REFERENCES album (album_id) ON DELETE CASCADE,
    duration TIME NOT NULL,
    lyrics TEXT
);

CREATE TABLE IF NOT EXISTS genre (
    genre_id SERIAL PRIMARY KEY,
    title TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS album_genre (
    album_id INT REFERENCES album ON DELETE CASCADE,
    genre_id INT REFERENCES genre ON DELETE CASCADE,
    PRIMARY KEY (album_id, genre_id)
);

CREATE TABLE IF NOT EXISTS author_genre (
    author_id INT REFERENCES author ON DELETE CASCADE,
    genre_id INT REFERENCES genre ON DELETE CASCADE,
    PRIMARY KEY (author_id, genre_id)
);

CREATE TYPE author_type AS ENUM('main', 'feat', 'remix');

CREATE TABLE IF NOT EXISTS author_track (
    author_id INT REFERENCES author ON DELETE CASCADE,
    track_id INT REFERENCES track ON DELETE CASCADE,
    PRIMARY KEY (author_id, track_id),
    "type" author_type NOT NULL
);

-- Individual Task 1.2

CREATE TABLE IF NOT EXISTS "user" (
    user_id SERIAL PRIMARY KEY,
    "name" TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS playlist (
    playlist_id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    user_id INT REFERENCES "user" ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS playlist_track (
    playlist_id INT REFERENCES playlist ON DELETE CASCADE,
    track_id INT REFERENCES track ON DELETE CASCADE,
    PRIMARY KEY (playlist_id, track_id)
);




