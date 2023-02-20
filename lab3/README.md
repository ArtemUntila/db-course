# Язык SQL DML

## Цели работы
Познакомиться с языком создания запросов управления данными SQL-DML.

## Программа работы
1. Изучение SQL-DML.
2. Выполнение всех запросов из списка стандартных запросов. Демонстрация результатов преподавателю.
3. Получение у преподавателя и реализация SQL-запросов в соответствии с **индивидуальным заданием**.
   Демонстрация результатов преподавателю.
4. Сохранение в БД выполненных запросов `SELECT` в виде представлений, запросов `INSERT`, `UPDATE` или `DELETE` &mdash; в виде ХП. 
   Выкладывание скрипта в GitLab.

## Ход работы

Все запросы собраны в одном SQL файле &mdash; lab3.sql.

Перед выполнением запросов сгенерированы данные для 10 исполнителей при помощи генратора, разработанного в ЛР 2.
В результате в БД записаны: 10 исполнителей; 22 альбома; 133 трека; 50 жанров.

### Выборка всех данных

Выборка всех исполнителей:
```sql
SELECT * FROM author;
```

|  author_id | title      | country    |
|-----------:|:-----------|:-----------|
|          1 | author_1   | Russia     |
|          2 | author_2   | Australia  |
|          3 | author_3   | Germany    |
|          4 | author_4   | England    |
|          5 | author_5   | France     |
|          6 | author_6   | Finland    |
|          7 | author_7   | Germany    |
|          8 | author_8   | Russia     |
|          9 | author_9   | Norway     |
|         10 | author_10  | Italy      |

### Выборка данных с условием

Выборка исполнителей из России:
```sql
SELECT * FROM author
WHERE country LIKE 'Russia';
```

|  author_id | title     | country  |
|-----------:|:----------|:---------|
|          1 | author_1  | Russia   |
|          8 | author_8  | Russia   |


Выборка альбомов, выпущенных с начала XXI века:
```sql
SELECT * FROM album
WHERE release BETWEEN '2000-01-01' AND NOW();
```

|  album_id | type    | title     | release     |  author_id |
|----------:|:--------|:----------|:------------|-----------:|
|         7 | album   | album_7   | 2000-09-19  |          3 |
|        15 | single  | album_15  | 2004-11-22  |          7 |
|        16 | album   | album_16  | 2009-11-27  |          8 |
|        17 | album   | album_17  | 2008-03-25  |          8 |
|        19 | album   | album_19  | 2013-07-25  |          9 |
|        21 | EP      | album_21  | 2002-08-03  |          9 |
|        22 | album   | album_22  | 2004-06-03  |         10 |


Выборка треков из трёх определённых альбомов:
```sql
SELECT * FROM track
WHERE album_id IN (1, 15, 21);
```

|  track_id | title      |  album_id | duration  | lyrics                                                                  |
|----------:|:-----------|----------:|:----------|:------------------------------------------------------------------------|
|         1 | track_1    |         1 | 00:04:08  | crSgFmnWBItZJKUVIezsjTuxyiydys                                          |
|         2 | track_2    |         1 | 00:09:47  | VejvrAaWRxjMnyJPSyBS HMVWsBaCGgzuerLfzsYbSFdPJdLYfQST                   |
|         3 | track_3    |         1 | 00:05:10  | DwwNnqnLaiPCJMKRjyDKIC                                                  |
|         4 | track_4    |         1 | 00:08:27  | pPuxkUZicfHhcQqWUYxOjGmuugbFam                                          |
|         5 | track_5    |         1 | 00:00:27  | RazeoyOLMsWuPSMsqdsYFbIuExPsn                                           |
|        96 | track_96   |        15 | 00:00:40  | HHZkunUIJtzNPItedHUpTYOCBCSIKrVEbeBUaVqtqu HfIQpiRJ                     |
|       124 | track_124  |        21 | 00:03:37  | iGxDXBsJbe                                                              |
|       125 | track_125  |        21 | 00:05:37  | jSQBPuIxWNNjwnyLZIMnDPJQbwLqivXGyfzvTpcvMJoTXwISegX FEgimtsZGVhuOBxTMB  |
|       126 | track_126  |        21 | 00:09:05  | SRDAsYxdgiNbLVqYB DjIahndxtignPHkgzpmHodXzdwodeBzkGYvTKTuJjdXLlWNJglP   |
|       127 | track_127  |        21 | 00:05:10  | ROQtxiDuJZMYNAyrDfIvNPj kgXjKyexgCQbWWwdJW                              |


### Вычисляемое поле в запросе

Т. к. таблицы не содержат атрибутов типа `INT` (помимо первичных и внешних ключей), то вычисления производились над 
типом `DATE` с использованием соответствующих встроенных функций.

Выборка всех альбомов с вычислением их возраста:
```sql
SELECT type, title, release, DATE_PART('year', AGE(NOW(), release)) AS album_age
FROM album;
```

| type    | title     | release     |  album_age |
|:--------|:----------|:------------|-----------:|
| album   | album_1   | 1998-12-07  |         23 |
| album   | album_2   | 1976-11-22  |         45 |
| album   | album_3   | 1998-04-06  |         24 |
| album   | album_4   | 1972-11-02  |         49 |
| album   | album_5   | 1999-11-29  |         22 |
| EP      | album_6   | 1998-10-05  |         23 |
| album   | album_7   | 2000-09-19  |         21 |
| album   | album_8   | 1997-04-03  |         25 |
| album   | album_9   | 1980-05-24  |         41 |
| album   | album_10  | 1987-04-05  |         35 |
| album   | album_11  | 1990-07-18  |         31 |
| EP      | album_12  | 1981-01-12  |         41 |
| album   | album_13  | 1975-06-08  |         46 |
| album   | album_14  | 1990-01-13  |         32 |
| single  | album_15  | 2004-11-22  |         17 |
| album   | album_16  | 2009-11-27  |         12 |
| album   | album_17  | 2008-03-25  |         14 |
| single  | album_18  | 1987-05-22  |         34 |
| album   | album_19  | 2013-07-25  |          8 |
| EP      | album_20  | 1971-06-20  |         50 |
| EP      | album_21  | 2002-08-03  |         19 |
| album   | album_22  | 2004-06-03  |         17 |


### Выборка всех данных с сортировкой по нескольким полям

Выборка всех альбомов с сортировкой по типу, а **затем** по убыванию даты: 
```sql
SELECT * FROM album
ORDER BY type, release DESC;
```

|  album_id | type    | title     | release     |  author_id |
|----------:|:--------|:----------|:------------|-----------:|
|        19 | album   | album_19  | 2013-07-25  |          9 |
|        16 | album   | album_16  | 2009-11-27  |          8 |
|        17 | album   | album_17  | 2008-03-25  |          8 |
|        22 | album   | album_22  | 2004-06-03  |         10 |
|         7 | album   | album_7   | 2000-09-19  |          3 |
|         5 | album   | album_5   | 1999-11-29  |          2 |
|         1 | album   | album_1   | 1998-12-07  |          1 |
|         3 | album   | album_3   | 1998-04-06  |          2 |
|         8 | album   | album_8   | 1997-04-03  |          3 |
|        11 | album   | album_11  | 1990-07-18  |          5 |
|        14 | album   | album_14  | 1990-01-13  |          7 |
|        10 | album   | album_10  | 1987-04-05  |          4 |
|         9 | album   | album_9   | 1980-05-24  |          3 |
|         2 | album   | album_2   | 1976-11-22  |          1 |
|        13 | album   | album_13  | 1975-06-08  |          6 |
|         4 | album   | album_4   | 1972-11-02  |          2 |
|        15 | single  | album_15  | 2004-11-22  |          7 |
|        18 | single  | album_18  | 1987-05-22  |          9 |
|        21 | EP      | album_21  | 2002-08-03  |          9 |
|         6 | EP      | album_6   | 1998-10-05  |          2 |
|        12 | EP      | album_12  | 1981-01-12  |          6 |
|        20 | EP      | album_20  | 1971-06-20  |          9 |


### Вычисление нескольких совокупных характеристик

Вычисление минимальной, максимальной, средней и суммарной длительности всех треков:
```sql
SELECT MIN(duration) AS min_duration, MAX(duration) AS max_duraion,
       AVG(duration)::time AS avg_duration, SUM(duration)::time AS sum_duration
FROM track;
```

`AVG(duration)` и `SUM(duration)` возврщают тип `interval`, поэтому, для консистентности выводимых значений, результаты
двух этих функций приведены к типу `time` с помощью `::time`.

| min_duration | max_duration | avg_duration | sum_duration |
|:-------------|:-------------|:-------------|:-------------|
| 00:00:01     | 00:09:59     | 00:04:57     | 10:58:35     |


### Выборка из связанных таблиц

Выборка из таблиц связанных "напрямую" (альбом и исполнитель):
```sql
SELECT author.title, album.title, type, release, country
FROM author JOIN album USING (author_id);
```

Если названия атрибутов/полей у таблиц пересекаются, то нужно явно указывать имя таблицы в имени атрибута `таблица.атрибут`.

Если имя внешнего ключа совпадает с именем первичного ключа, на который он ссылается, то вместо 
`table_1 JOIN table_2 ON table_1.attribute_1 = table_2.attribute_2` можно использовать `table_1 JOIN table_2 USING(attribute)`.

| author.title | album.title | type   | release    | country   |
|:-------------|:------------|:-------|:-----------|:----------|
| author_1     | album_1     | album  | 1998-12-07 | Russia    |
| author_1     | album_2     | album  | 1976-11-22 | Russia    |
| author_2     | album_3     | album  | 1998-04-06 | Australia |
| author_2     | album_4     | album  | 1972-11-02 | Australia |
| author_2     | album_5     | album  | 1999-11-29 | Australia |
| author_2     | album_6     | EP     | 1998-10-05 | Australia |
| author_3     | album_7     | album  | 2000-09-19 | Germany   |
| author_3     | album_8     | album  | 1997-04-03 | Germany   |
| author_3     | album_9     | album  | 1980-05-24 | Germany   |
| author_4     | album_10    | album  | 1987-04-05 | England   |
| author_5     | album_11    | album  | 1990-07-18 | France    |
| author_6     | album_12    | EP     | 1981-01-12 | Finland   |
| author_6     | album_13    | album  | 1975-06-08 | Finland   |
| author_7     | album_14    | album  | 1990-01-13 | Germany   |
| author_7     | album_15    | single | 2004-11-22 | Germany   |
| author_8     | album_16    | album  | 2009-11-27 | Russia    |
| author_8     | album_17    | album  | 2008-03-25 | Russia    |
| author_9     | album_18    | single | 1987-05-22 | Norway    |
| author_9     | album_19    | album  | 2013-07-25 | Norway    |
| author_9     | album_20    | EP     | 1971-06-20 | Norway    |
| author_9     | album_21    | EP     | 2002-08-03 | Norway    |
| author_10    | album_22    | album  | 2004-06-03 | Italy     |


Выборка из таблиц, связанных через таблицу-связку (исполнитель и жанр):
```sql
SELECT author.title as author, genre.title as genre
FROM author
    JOIN author_genre USING (author_id)
    JOIN genre USING (genre_id);
```

| author    | genre            |
|:----------|:-----------------|
| author_1  | Jazz             |
| author_2  | Progressive Rock |
| author_3  | Gothic Metal     |
| author_4  | New Age          |
| author_5  | Hard Rock        |
| author_6  | Metal            |
| author_7  | Post Hardcore    |
| author_8  | Post Hardcore    |
| author_9  | Thrash Metal     |
| author_10 | Rock             |


### Совокупная характеристика с использованием группировки (+ ограничение на результат группировки)

Выборка альбомов с группировкой по исполнителю (+ выбором только тех исполнителей, у которых более 3 альбомов включительно):
```sql
SELECT author_id, COUNT(album_id) as albums_count
FROM album
GROUP BY author_id
HAVING COUNT(album_id) >= 3;
```

В СУБД **PostgreSQL** порядок выполнения операторов таков, что рассчитанную совокупную характеристику `albums_count` нельзя использовать
в операторе `HAVING` (в отличие, например, от **MySQL**) и `COUNT(album_id)` нужно вычислять (по крайней мере записывать в такой форме) ещё раз.

|  author_id | albums_count |
|-----------:|-------------:|
|          9 |            4 |
|          3 |            3 |
|          2 |            4 |


### Использование вложенного запроса

Вычисление максимального числа альбомов у одного исполнителя (вложенный запрос подсчитывает число альбомов у каждого исполнителя):
```sql
SELECT MAX(count) FROM (
    SELECT COUNT(album_id) as count
    FROM album
    GROUP BY author_id
) as tmp;
```

| max |
|----:|
|   4 |

### Изменение значения у записи, отвечающей заданному условию

Для выполнения этого и одного из следующих запросов в таблицу `author` внесена новая запись:
```sql
INSERT INTO author (title, country) VALUES ('Delain', 'Holland');
```

| author_id | title  | country |
|----------:|:-------|:--------|
|        11 | Delain | Holland |

Изменение названия страны у исполнителей с "Голландия" на "Нидерланды":
```sql
UPDATE author
SET country = 'Netherlands'
WHERE country = 'Holland';
```

| author_id | title  | country     |
|----------:|:-------|:------------|
|        11 | Delain | Netherlands |


### Удалиние записи, имеющей максимальное (минимальное) значение некоторой совокупной характеристики

Удаление треков с минимальной длительностью:
```sql
DELETE FROM track
WHERE duration = (SELECT MIN(duration) FROM track);
```

До:

| track_id | title    | album_id | duration | lyrics                                                                                   |
|---------:|:---------|---------:|:---------|:-----------------------------------------------------------------------------------------|
|       25 | track_25 |        4 | 00:03:27 | vHOrGaIzpoztVPAnjLV                                                                      |
|       26 | track_26 |        4 | 00:00:01 | hRcvV OQnugrrOaDTPweDGI                                                                  |
|       27 | track_27 |        4 | 00:01:42 | BwLnUCiGBqEMR pBqGSWjhpVkpTYgulOdzkFFSpyiwgRBTINZkBufKNTMAFasdXqpYGpZlaVWorxKXkgSaxisCSM |

После:

| track_id | title    | album_id | duration | lyrics                                                                                   |
|---------:|:---------|---------:|:---------|:-----------------------------------------------------------------------------------------|
|       25 | track_25 |        4 | 00:03:27 | vHOrGaIzpoztVPAnjLV                                                                      |
|       27 | track_27 |        4 | 00:01:42 | BwLnUCiGBqEMR pBqGSWjhpVkpTYgulOdzkFFSpyiwgRBTINZkBufKNTMAFasdXqpYGpZlaVWorxKXkgSaxisCSM |


### Удаление записей в главной таблице, на которые не ссылается подчиненная таблица (используя вложенный запрос)

На добавленную и изменённую ранее в таблицу `author` запись `'Delain', 'Netherlands'` не ссылается ни одна запись из 
подчинённой таблицы `album`.

Удаление исполнителей без единого альбома:
```sql
DELETE FROM author
WHERE author_id NOT IN (
    SELECT DISTINCT author_id FROM album
);
```

Во вложенном запросе `SELECT` использована опиця `DISTINCT`, которая убирает повторяющиеся значения, т. к. к одному 
исполнителю могут ссылаться несколько альбомов и один и тот же `author_id` встречался бы несколько раз (это действие не
обязательно и запрос бы отработал корректно и без `DISTINCT`, но вариант с его использоваием кажется и выглядит более "правильным" и "красивым").

До:

| author_id | title     | country     |
|----------:|:----------|:------------|
|         9 | author_9  | Norway      |
|        10 | author_10 | Italy       |
|        11 | Delain    | Netherlands |

После:

| author_id | title     | country     |
|----------:|:----------|:------------|
|         9 | author_9  | Norway      |
|        10 | author_10 | Italy       |


## Индивидуальное задание

[individual_task.sql](sql/individual_task.sql)

### Задание 1

Вывести топ-5 самых популярных жанров исходя из количества соответствующих треков в плейлистах пользователей.

```sql
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
```

Жанр трека определяется по жанру альбома. Производится группировка по жанру и подсчитывается число треков, после чего
результат сортируется по убыванию числа треков каждого жанра и берутся первые 5 элементов.

| genre         | count |
|:--------------|------:|
| Folk Rock     |    19 |
| Classical     |    16 |
| Drum and Bass |    13 |
| Thrash Metal  |    11 |
| Electronic    |    10 |


## Задание 2

Написать хранимую процедуру/функцию, которая для заданного автора (параметр процедуры) будет выводить список его альбомов 
с указанием общей длительности входящих в каждый альбом треков (в **минутах**) и жанра каждого альбома 
(если у альбома несколько жанров, их нужно агрегировать черех запятую).

Чтобы вычислить общую длительность альбома в минутах из суммы длительности треков берётся общее число секунд и делится
на 60, после чего полученный результат округляется в меньшую сторону.

```sql
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
```

Параметр функции &mdash; идентификатор исполнителя. Производится группировка треков и жанров по альбому: суммируются
длительности треков, жанры агрегируются через запятую (функция `STRING_AGG()`). Чтобы один и тот же жанр не повторялся
(для каждого трека), использована опция `DISTINCT`.

| album_title | duration_in_minutes | genres                              |
|:------------|--------------------:|:------------------------------------|
| album_1     |                  64 | Progressive Metal, Progressive Rock |
| album_2     |                  36 | Disco                               |
| album_3     |                  37 | Electronic                          |


### Задание 3

Написать хранимую процедуру/функцию, которая выводит плейлисты заданного пользователя (параметр процедуры) в формате JSON. 
При этом каждый трек должен описываться одной строкой по следующим правилам:
* вначале записывается главный автор или авторы (через запятую)
* далее идет тире
* затем название трека
* в круглых скобках указываются feat-авторы, если они есть
* в квадратных скобках указывается чей это ремикс, если трек является таковым

Шаблон: `<main author(s)> — (feat. <feat author(s)>) [<remix author(s)> Remix]`

Пример общего формата:
```json
[
  {
    "playlist_title": "Playlist 1",
    "tracks": [
      "The Rolling Stones — Paint It Black",
      "Flo Rida — Wild Ones (feat. Sia)",
      "Wamdue Project — King Of My Castle [Roy Malone Remix]",
     ]
  },
  {
    "playlist_title": "Playlist 2",
    "tracks": [ "<...>" ]
  }
]
```

Для "красиво" отформатированного вывода использована функция `jsonb_pretty`, работающая с типом `jsonb`, в котором
атрибуты сортируются по количеству символов, поэтому, чтобы сначала шло название плейлиста, а потом &mdash; список треков, 
атрибутам заданы следующие имена: `"playlist"` и `"track list"`. Формирование/агрегирование `json` из выборки 
производится при помощи функции `json_agg`.

```sql
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
```

Все нижеперечисленные функции имеют 1 параметр &mdash; идентификатор трека и возвращают данные типа `TEXT`:
* `get_main_authors` &mdash; "главные"/"основные" исполнители: `author_1` или `author_1, author_2`
* `get_feat_authors` &mdash; совместные исполнители: ` (feat. author_1)` или ` feat. author1, author2`
* `get_remix_authors` &mdash; авторы ремикса: ` [author_1 Remix]` или ` [author_1, author_2 Remix]`
* `get_track_canonical_title` &mdash; название трека в соответствии с заданным шаблоном: `author_1, author_2 — track_1 (feat. author_3, author_4) [author_5, author_6 Remix]`

`get_playlists_pretty_jsonb` &mdash; основная функция: параметр &mdash; индетификатор пользователя; возвращает "красиво"
отформатированное `json`(`jsonb`)-представление плейлистов пользователя с указанным идентификатором (возвращаемый тип &mdash; `TEXT`).

```json
[
   {
      "playlist": "playlist_32",
      "track list": [
         "author_9 — track_143",
         "author_9 — track_146",
         "author_4, author_1 — track_75",
         "author_3 — track_63",
         "author_9 — track_136",
         "author_2 — track_46 (feat. author_9)",
         "author_1 — track_13",
         "author_3 — track_60 [author_5 Remix]",
         "author_6 — track_96"
      ]
   },
   {
      "playlist": "playlist_33",
      "track list": [
         "author_9 — track_137 (feat. author_10) [author_6 Remix]",
         "author_4 — track_67",
         "author_10, author_7 — track_166 (feat. author_1, author_9) [author_5, author_6 Remix]",
         "author_10 — track_179",
         "author_2 — track_46 (feat. author_9)",
         "author_3 — track_55"
      ]
   },
   {
      "playlist": "playlist_34",
      "track list": [
         "author_6 — track_106"
      ]
   }
]
```


## Выводы

В ходе выполнения лабораторной работы теоретически и практически изучен язык создания запросов управления данными SQL-DM.
С его помощью выполнен ряд запросов на выборку и корректировку данных с использованием различных синтаксический конструкций
и операторов: вычисляемые поля, задание условий, расчёт совокупных характеристик, группировка, наложение ограничений на 
результат группировки, соединение таблиц, изменение и удаление записей, встроенные функции и т. д.