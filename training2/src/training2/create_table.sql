--postgresへの接続
--psql -p ポート番号 -U ユーザ名

--データベースの作成
CREATE DATABASE students;

--各テーブルの削除
DROP TABLE Class CASCADE;
DROP TABLE Subject CASCADE;
DROP TABLE Student CASCADE;
DROP TABLE Record CASCADE;

--クラステーブルの作成
CREATE TABLE Class
(
	class_id SERIAL,
	class_name CHAR(1),
	created_at DATE,
	updated_at DATE,
	PRIMARY KEY (class_id)
);


--教科テーブルの作成
CREATE TABLE Subject
(
	subject_id SERIAL,
	subject_name VARCHAR(4),
	created_at DATE,
	updated_at DATE,
	PRIMARY KEY (subject_id)
);

--生徒テーブルの作成
CREATE TABLE Student
(
	number SERIAL,
	class INTEGER,
	name VARCHAR(20),
	created_at DATE,
	updated_at DATE,
	PRIMARY KEY (number),
	FOREIGN KEY(class)
	REFERENCES Class(class_id)
);

--成績テーブルの作成
CREATE TABLE Record
(
	record_id SERIAL,
	number INTEGER,
	subject_id INTEGER,
	record INTEGER,
	created_at DATE,
	updated_at DATE,
	PRIMARY KEY (record_id),
	FOREIGN KEY (number)
	REFERENCES Student(number),
	FOREIGN KEY (subject_id)
	REFERENCES Subject(subject_id)
);

--クラステーブルへの行の挿入
INSERT INTO Class (class_name, created_at, updated_at) VALUES ('A', CURRENT_DATE, CURRENT_DATE);
INSERT INTO Class (class_name, created_at, updated_at) VALUES ('B', CURRENT_DATE, CURRENT_DATE);
INSERT INTO Class (class_name, created_at, updated_at) VALUES ('C', CURRENT_DATE, CURRENT_DATE);

--教科テーブルへの行の挿入
INSERT INTO Subject (subject_name, created_at, updated_at) VALUES ('英語', CURRENT_DATE, CURRENT_DATE);
INSERT INTO Subject (subject_name, created_at, updated_at) VALUES ('数学', CURRENT_DATE, CURRENT_DATE);
INSERT INTO Subject (subject_name, created_at, updated_at) VALUES ('国語', CURRENT_DATE, CURRENT_DATE);
INSERT INTO Subject (subject_name, created_at, updated_at) VALUES ('社会', CURRENT_DATE, CURRENT_DATE);
INSERT INTO Subject (subject_name, created_at, updated_at) VALUES ('理科', CURRENT_DATE, CURRENT_DATE);
