DROP DATABASE IF EXISTS onlineBookStore;
CREATE DATABASE IF NOT EXISTS onlineBookStore;
USE onlineBookStore;

CREATE TABLE IF NOT EXISTS client (
  id       INT         NOT NULL AUTO_INCREMENT,
  name     VARCHAR(45) NOT NULL,
  address  VARCHAR(45) NOT NULL,
  email    VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS purchase_history (
  purchase_date TIMESTAMP NOT NULL,
  client_id     INT       NOT NULL,
  book_id       INT       NOT NULL,
  PRIMARY KEY (client_id, book_id)
)
  ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS book (
  id        INT         NOT NULL AUTO_INCREMENT,
  name      VARCHAR(45) NOT NULL,
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
  ('Author 1', '20'),
  ('Author 2', '19');

INSERT INTO book (name, in_stock, author_id)
VALUES
  ('Test book 1', '11', '1'),
  ('Test book 2', '14', '1'),
  ('Test book 3', '17', '2'),
  ('Test book 4', '10', '2');

INSERT INTO client(name, address, email, password)
VALUES
  ('Test client 1', 'Test address 1', 'Test email 1', '123'),
  ('Test client 2', 'Test address 2', 'Test email 2', '123'),
  ('Test client 3', 'Test address 3', 'Test email 3', '123'),
  ('Test client 4', 'Test address 4', 'Test email 4', '123');

INSERT INTO purchase_history(purchase_date, client_id, book_id)
VALUES
  ('17.02.2015', '1', '1'),
  ('15.12.2015', '1', '2'),
  ('12.11.2015', '2', '1'),
  ('12.09.2015', '2', '2');
# CREATE TABLE IF NOT EXISTS current_borrows (
#   date      TIMESTAMP NOT NULL,
#   validity  TIMESTAMP NOT NULL,
#   client_id INT       NOT NULL,
#   book_id   INT       NOT NULL,
#   PRIMARY KEY (client_id, book_id)
# )
#   ENGINE = InnoDB;