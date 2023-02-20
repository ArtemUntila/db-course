-- Выборка всех данных из таблицы
SELECT * FROM author;


-- Выборка данных с условием
SELECT * FROM author
WHERE country LIKE 'Russia';

SELECT * FROM album
WHERE release BETWEEN '2000-01-01' AND NOW();

SELECT * FROM track
WHERE album_id IN (1, 15, 21);


-- Вычисляемое поле в запросе
SELECT type, title, release, DATE_PART('year', AGE(NOW(), release)) AS album_age
FROM album;


-- Сортировка по несколькм полям
SELECT * FROM album
ORDER BY type, release DESC;


-- Вычисление нескольких совокупных характеристик
SELECT MIN(duration) AS min_duration, MAX(duration) AS max_duraion,
       AVG(duration)::time AS avg_duration, SUM(duration)::time AS sum_duration
FROM track;


-- Выборка из связанных таблиц
SELECT author.title, album.title, type, release, country
FROM author JOIN album USING (author_id);

SELECT author.title as author, genre.title as genre
FROM author
    JOIN author_genre USING (author_id)
    JOIN genre USING (genre_id);


-- Совокупная характеристика с использованием группировки (+ ограничение на результат группировки)
SELECT author_id, COUNT(album_id) as albums_count
FROM album
GROUP BY author_id
HAVING COUNT(album_id) >= 3;


-- Вложенный запрос
SELECT MAX(count) FROM (
    SELECT COUNT(album_id) as count
    FROM album
    GROUP BY author_id
) as tmp;


INSERT INTO author (title, country) VALUES ('Delain', 'Holland');
-- Изменение значения нескольких полей у всех записей, отвечающих заданному условию
UPDATE author
SET country = 'Netherlands'
WHERE country = 'Holland';


-- Удалиние записи, имеющей максимальное (минимальное) значение некоторой совокупной характеристики
DELETE FROM track
WHERE duration = (SELECT MIN(duration) FROM track);


-- Удаление записей в главной таблице, на которые не ссылается подчиненная таблица (используя вложенный запрос)
DELETE FROM author
WHERE author_id NOT IN (
    SELECT DISTINCT author_id FROM album
);
