
CREATE KEYSPACE IF NOT EXISTS user_keyspace WITH REPLICATION = 
{ 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

DROP TABLE user_keyspace.user_info;
DROP TABLE user_keyspace.user_by_phonenum;
DROP TABLE user_keyspace.user_by_username;
DROP TABLE user_keyspace.user_by_email;

USE user_keyspace;

CREATE TABLE user_info (
	id uuid,
	username text,
	password text,
	firstname text,
	lastname text,
	email text,
	phonenum text,
	register_from text,
	created timestamp,
	last_modified timestamp,
	PRIMARY KEY (id)
);

CREATE TABLE user_by_phonenum(
	phonenum text PRIMARY KEY,
	id uuid
);

CREATE TABLE user_by_username(
	username text PRIMARY KEY,
	id uuid
);

CREATE TABLE user_by_email(
	email text PRIMARY KEY,
	id uuid
);