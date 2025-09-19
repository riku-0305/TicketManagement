CREATE TABLE gym_user (
  id varchar(20) NOT NULL PRIMARY KEY,
  name varchar(30) NOT NULL,
  email_address varchar(50) NOT NULL,
  membership_fee int NOT NULL,
  admission_day date NOT NULL
);

CREATE TABLE tickets (
  ticket_number INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id varchar(20) NOT NULL,
  remaining int NOT NULL,
  buy_day date NOT NULL,
  user_name varchar(30) NOT NULL,
  ticket_name varchar(30) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES gym_user (id)
);
