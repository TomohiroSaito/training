--postgresへの接続
--psql -p ポート番号 -U ユーザ名

--データベースの作成
CREATE DATABASE students;

--各テーブルの削除
DROP TABLE Class;
DROP TABLE Subject;
DROP TABLE Student;
DROP TABLE Record;

--クラステーブルの作成
CREATE TABLE Class
(
	class_id	INTEGER,
	class_name	CHAR(1),
	created_at	DATE		NOT NULL,
	updated_at	DATE		NOT NULL,
	PRIMARY KEY (class_id)
);


--教科テーブルの作成
CREATE TABLE Subject
(
	subject_id		INTEGER,
	subject_name	VARCHAR(4),
	created_at		DATE		NOT NULL,
	updated_at		DATE		NOT NULL,
	PRIMARY KEY (subject_id)
);

--生徒テーブルの作成
CREATE TABLE Student
(
	number		INTEGER,
	class		INTEGER		NOT NULL,
	name		VARCHAR(20),
	created_at 	DATE 		NOT NULL,
	updated_at 	DATE 		NOT NULL,
	PRIMARY KEY (number),
	FOREIGN KEY(class)
	REFERENCES Class(class_id)
);

--成績テーブルの作成
CREATE TABLE Record
(
	record_id	INTEGER,
	number		INTEGER		NOT NULL,
	subject_id	INTEGER		NOT NULL,
	record		INTEGER,
	created_at	DATE		NOT NULL,
	updated_at	DATE		NOT NULL,
	PRIMARY KEY (record_id),
	FOREIGN KEY (number)
	REFERENCES Student(number),
	FOREIGN KEY (subject_id)
	REFERENCES Subject(subject_id)
);
