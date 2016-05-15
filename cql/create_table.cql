
CREATE KEYSPACE IF NOT EXISTS UserKeyspace WITH REPLICATION = 
{ 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

DROP TABLE UserKeyspace.UserInfo;

CREATE TABLE UserKeyspace.UserInfo (
	id uuid,
	nickname text, #用户名
	password text,
	firstname text, #名
	lastname text, #姓氏
	email text,
	phone text,
	from text,
	created timestamp,
	last_modified timestamp,
	PRIMARY KEY (id)
);