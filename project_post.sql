
/* Drop Tables */

DROP TABLE IF EXISTS comment_tb;
DROP TABLE IF EXISTS post_tag_tb;
DROP TABLE IF EXISTS post_tb;
DROP TABLE IF EXISTS tag_tb;
DROP TABLE IF EXISTS user_tb;




/* Create Tables */

CREATE TABLE comment_tb
(
	id BIGSERIAL  NOT NULL,
	post_id int,
	user_id int,
	content varchar(60),
	create_at date,
	update_at date,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE post_tag_tb
(
	id BIGSERIAL  NOT NULL,
	post_id int,
	tag_id int,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE post_tb
(
	id BIGSERIAL  NOT NULL,
	title varchar(100),
	user_id int,
	content varchar(300),
	create_at date,
	update_at date,
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE tag_tb
(
	id BIGSERIAL  NOT NULL,
	name char(40),
	PRIMARY KEY (id)
) WITHOUT OIDS;


CREATE TABLE user_tb
(
	id BIGSERIAL  NOT NULL,
	username varchar(60),
	email varchar(60),
	password varchar(60),
	role int,
	active boolean,
	create_at date,
	update_at date,
	PRIMARY KEY (id)
) WITHOUT OIDS;





