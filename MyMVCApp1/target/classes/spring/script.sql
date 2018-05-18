CREATE TABLE mymemodata (
  id INTEGER PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(100) NOT NULL,
  content VARCHAR(255),
);

INSERT INTO mymemodata (title, content) VALUES('Hello!', 'This is sample message.');
INSERT INTO mymemodata (title, content) VALUES('welcome', 'welcome to spring mvc!');
