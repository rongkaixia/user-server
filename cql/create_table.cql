
CREATE KEYSPACE IF NOT EXISTS user_keyspace WITH REPLICATION = 
{ 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

DROP TABLE user_keyspace.user_info;
DROP TABLE user_keyspace.user_by_phonenum;
DROP TABLE user_keyspace.user_by_email;
DROP TABLE user_keyspace.auth;


USE user_keyspace;

CREATE TABLE user_info (
	id text,
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
	phonenum text,
	password text, //for fast retreiving password, instead of quering user_info table
	id text,
	PRIMARY KEY(phonenum, password)
);

CREATE TABLE user_by_email(
	email text,
	password text,
	id text,
	PRIMARY KEY(email, password)
);

CREATE TABLE auth(
	auth_access_token text,
	auth_name text,
	auth_id text,
	user_id text,
	auth_expires timestamp,
	PRIMARY KEY(auth_access_token, auth_name, auth_id)
);

CREATE INDEX ON auth(auth_name);
