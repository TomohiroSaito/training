CREATE TABLE mypersondata (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	mail VARCHAR(50),
	age INTEGER
);

INSERT INTO mypersondata (name, mail, age) VALUES('taro', 'taro@yamada', 34);
INSERT INTO mypersondata (name, mail, age) VALUES('hanako', 'hanako@flower', 28);