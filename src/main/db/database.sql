CREATE DATABASE blog ENCODING 'UTF-8';

CREATE TABLE IF NOT EXISTS roles (
	id 			SERIAL PRIMARY KEY,
	role 		VARCHAR(25) NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
	id 			SERIAL PRIMARY KEY,
	username	VARCHAR(25) NOT NULL,
	password    VARCHAR(25) NOT NULL,
	role_id		INT REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS posts (
	id 			SERIAL PRIMARY KEY,
	text	 	VARCHAR(255) NOT NULL,
	user_id	    INT REFERENCES users(id)
);

INSERT INTO roles (id, role) VALUES (DEFAULT, 'ADMIN');
INSERT INTO roles (id, role) VALUES (DEFAULT, 'USER');

INSERT INTO users (id, username, password, role_id) VALUES (DEFAULT, 'Dima', 'pass1', 1);
INSERT INTO users (id, username, password, role_id) VALUES (DEFAULT, 'Maks', 'pass2', 2);
INSERT INTO users (id, username, password, role_id) VALUES (DEFAULT, 'Mark', 'pass3', 2);

INSERT INTO posts (id, text, user_id) VALUES (DEFAULT, 'qwertyuiop', 1);
INSERT INTO posts (id, text, user_id) VALUES (DEFAULT, 'asdfghjkl', 2);