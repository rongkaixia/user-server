
CREATE KEYSPACE IF NOT EXISTS user_keyspace WITH REPLICATION = 
{ 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

DROP TABLE user_keyspace.user_info;
DROP TABLE user_keyspace.user_by_phonenum;
DROP TABLE user_keyspace.user_by_username;
DROP TABLE user_keyspace.user_by_email;
DROP TABLE user_keyspace.auth;


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
	phonenum text,
	id uuid,
	password text //for fast retreiving password, instead of quering user_info table
	PRIMARY KEY(phonenum, password)
);

CREATE TABLE user_by_username(
	username text,
	id uuid,
	password text，
	PRIMARY KEY(usename, password)
);

CREATE TABLE user_by_email(
	email text,
	id uuid,
	password text,
	PRIMARY KEY(email, password)
);

CREATE TABLE auth(
	id uuid,
	oauth_access_token text,
	oauth_name text,
	oauth_id text,
	user_id uuid,
	oauth_expires timestamp,
	PRIMARY KEY(id, oauth_access_token, oauth_name, oauth_id)
);

CREATE INDEX ON auth(oauth_name);
