CREATE TABLE pet (
	pet_id VARCHAR(50) PRIMARY KEY,
	pet_name VARCHAR(50),
	owner_name VARCHAR(50),
	price VARCHAR(50),
	birth_date DATE
);

INSERT INTO pet VALUES('1', 'pochi', 'shinici', '50000', now());
