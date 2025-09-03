CREATE TABLE gym_user (
  id varchar(20) NOT NULL PRIMARY KEY,
  name varchar(30) NOT NULL,
  email_address varchar(50) NOT NULL,
  membership_fee int NOT NULL,
  admission_day date NOT NULL
);

CREATE TABLE nomination_ticket (
  id varchar(20) NOT NULL PRIMARY KEY,
  remaining int NOT NULL,
  buy_day date NOT NULL,
  user_name varchar(30) NOT NULL
);

CREATE TABLE stretch_ticket (
  id varchar(20) NOT NULL PRIMARY KEY,
  remaining int NOT NULL,
  buy_day date NOT NULL,
  user_name varchar(30) NOT NULL
);