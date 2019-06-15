CREATE TABLE singer (
	id INT NOT NULL AUTO_INCREMENT,
	first_name VARCHAR(60) NOT NULL,
	last_name VARCHAR(40) NOT NULL,
	birth_date DATE,
	UNIQUE uq_singer_1 (first_name, last_name),
	PRIMARY KEY (id)
);

CREATE TABLE album (
	id INT NOT NULL AUTO_INCREMENT,
	singer_id INT NOT NULL,
	title VARCHAR(100) NOT NULL,
	release_date DATE,
	UNIQUE uq_singer_album_1 (singer_id, title),
	PRIMARY KEY (id),
	CONSTRAINT fk_album FOREIGN KEY (singer_id) REFERENCES singer (id)
);