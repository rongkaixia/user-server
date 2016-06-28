
CREATE KEYSPACE IF NOT EXISTS user_keyspace WITH REPLICATION = 
{ 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

DROP TABLE user_keyspace.user_info;
DROP TABLE user_keyspace.user_by_phonenum;
DROP TABLE user_keyspace.user_by_email;
DROP TABLE user_keyspace.auth;
DROP TABLE user_keyspace.security_question;


USE user_keyspace;

CREATE TABLE user_info (
	id text,
	password text,
	nickname text,
	truename text,
	email text,
	phonenum text,
	register_from text,
	security_question1 text,
	security_question2 text,
	security_question3 text,
	security_question1_ans text,
	security_question2_ans text,
	security_question3_ans text,
	created timestamp,
	last_modified timestamp,
	PRIMARY KEY (id)
);
CREATE INDEX ON user_info(email);
CREATE INDEX ON user_info(phonenum);

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
	user_nickname text,
	auth_expires timestamp,
	PRIMARY KEY(auth_access_token, auth_name, auth_id)
);
CREATE INDEX ON auth(auth_name);

CREATE TABLE security_question(
	id UUID,
	content text,
	PRIMARY KEY(id)
);
