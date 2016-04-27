DROP DATABASE IF EXISTS onlineBookStore;
CREATE DATABASE IF NOT EXISTS onlineBookStore;
USE onlineBookStore;

CREATE TABLE IF NOT EXISTS client (
  id        INT                  NOT NULL AUTO_INCREMENT,
  name      VARCHAR(45)          NOT NULL,
  address   VARCHAR(45)          NOT NULL,
  serial_id VARCHAR(45) UNIQUE   NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS client_account (
  id            INT         NOT NULL AUTO_INCREMENT,
  email         VARCHAR(45) NOT NULL,
  password      VARCHAR(45) NOT NULL,
  creation_date TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
  client_id     INT         NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS purchase_history (
  purchase_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  client_account_id INT NOT NULL,
  book_id           INT NOT NULL,
  PRIMARY KEY (client_account_id, book_id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS book (
  id        INT         NOT NULL AUTO_INCREMENT,
  title     VARCHAR(150) NOT NULL,
  genre     VARCHAR(45) NOT NULL,
  in_stock  INT         NOT NULL,
  author_id INT         NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS author (
  id   INT         NOT NULL AUTO_INCREMENT,
  name VARCHAR(45) NOT NULL,
  age  VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

INSERT INTO author (name, age)
VALUES
  ('Martin fowler', '45'),
  ('Robert C. Martin', '50'),
  ('Ian Sommerville', '44'),
  ('Gang of four', '500');

INSERT INTO book (title, genre, in_stock, author_id)
VALUES
  ('Patterns of Enterprise Application Architecture', 'Learning', '5', '1'),
  ('Domain specific languages', 'Learning', '5', '1'),
  ('Planning extreme programming', 'Learning', '5', '1'),
  ('Clean CODE ', 'Learning', '5', '2'),
  ('Clean coder', 'Learning', '5', '2'),
  ('UML FOR Java Programmers', 'Learning', '5', '2'),
  ('Software Engineering 9th Edition', 'Learning', '5', '3'),
  ('Software Engineering 10th Edition', 'Learning', '5', '3'),
  ('Design Patterns:Elements of Reusable Object-Oriented Software', 'Learning', '5', '4');

INSERT INTO client (name, address, serial_id)
VALUES
  (' CLIENT 1', 'Cluj, Marasti', 'CJ - 123'),
  (' CLIENT 2', 'Cluj, Manastur', 'CJ - 124'),
  (' CLIENT 3', 'Bucuresti, Sector 1', 'B-421'),
  (' CLIENT 4', 'Alba Iulia', 'AB-123');

INSERT INTO client_account (email, password, client_id)
VALUES
  ('client1@gmail.com', '123', '1'),
  ('client1.2@gmail.com', '123', '1'),
  ('client2@gmail.com', '123', '2'),
  ('client3@gmail.com', '123', '3'),
  ('client3.2@gmail.com', '123', '3'),
  ('client3.3@gmail.com', '123', '3'),
  ('client1@gmail.com', '123', '4');

INSERT INTO purchase_history (client_account_id, book_id)
VALUES
  ('1', '1'),
  ('1', '2'),
  ('2', '1'),
  ('2', '2');
