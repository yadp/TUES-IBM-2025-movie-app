DROP TABLE IF EXISTS "episode";
DROP TABLE IF EXISTS "involvement";
DROP TABLE IF EXISTS "rating";
DROP TABLE IF EXISTS "watch_history";
DROP TABLE IF EXISTS "review";
DROP TABLE IF EXISTS "media";
DROP TABLE IF EXISTS "user";

DROP TYPE IF EXISTS user_type;
DROP TYPE IF EXISTS genre_type;
DROP TYPE IF EXISTS media_type;
DROP TYPE IF EXISTS involvement_type;

CREATE TYPE user_type AS ENUM ('simple', 'artist', 'admin');
CREATE TYPE genre_type AS ENUM ('horror', 'action', 'adventure', 'crime');
CREATE TYPE media_type AS ENUM ('movie', 'show');
CREATE TYPE involvement_type AS ENUM ('director', 'producer', 'actor');

CREATE TABLE "user" (
	id SERIAL PRIMARY KEY,
	username VARCHAR(255) NOT NULL,
	email VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	type user_type NOT NULL
);

CREATE TABLE "media" (
	id SERIAL PRIMARY KEY,
	title VARCHAR(255) NOT NULL,
	year INT NOT NULL,
	description VARCHAR(5000) NOT NULL,
	duration INT NOT NULL,
	genre genre_type NOT NULL,
	type media_type NOT NULL,
	number_of_episodes INT,
	number_of_seasons INT,
	average_rating INT,
	ratings_count INT
);

create table "rating" (
	id SERIAL PRIMARY KEY,
	user_id INT NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
	media_id INT NOT NULL REFERENCES "media"(id) ON DELETE CASCADE,
	rating INT NOT NULL CHECK (rating >= 0 AND rating <= 10)
);

CREATE TABLE "watch_history" (
	id SERIAL PRIMARY KEY,
	user_id INT NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
	media_id INT NOT NULL REFERENCES "media"(id) ON DELETE CASCADE,
	date TIMESTAMP NOT NULL
);

create table "review" (
	id SERIAL PRIMARY KEY,
	contents VARCHAR(5000) NOT NULL,
	user_id INT NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
	media_id INT NOT NULL REFERENCES "media"(id) ON DELETE cascade,
	date TIMESTAMP NOT NULL
);

CREATE TABLE "involvement" (
	id SERIAL PRIMARY KEY,
	user_id INT NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
	media_id INT NOT NULL REFERENCES "media"(id) ON DELETE CASCADE,
	type involvement_type NOT NULL
);

CREATE TABLE "episode" (
	id SERIAL PRIMARY KEY,
	media_id INT NOT NULL REFERENCES "media"(id) ON DELETE CASCADE,
	season_number INT NOT NULL CHECK (season_number > 0),
	episode_number INT NOT NULL CHECK (episode_number > 0),
	title VARCHAR(255) NOT NULL,
	description VARCHAR(5000),
	duration INT NOT NULL,
	UNIQUE(media_id, season_number, episode_number)
);
