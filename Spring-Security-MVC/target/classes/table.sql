CREATE TABLE account(
    username varchar(128),
    password varchar(128),
    first_name varchar(128),
    last_name varchar(128),
    constraint pk_tbl_account primary key (username)
);

INSERT INTO account (username, password, first_name, last_name) VALUES ('maruko', 'maru18', 'saito', 'tomohiro');
