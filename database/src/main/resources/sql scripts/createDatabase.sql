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

# CREATE TABLE IF NOT EXISTS current_borrows (
#   date      TIMESTAMP NOT NULL,
#   validity  TIMESTAMP NOT NULL,
#   client_id INT       NOT NULL,
#   book_id   INT       NOT NULL,
#   PRIMARY KEY (client_id, book_id)
# )
#   ENGINE = InnoDB;