SET SQL_SAFE_UPDATES = 0;

##Create database
CREATE DATABASE IF NOT EXISTS agentie_presa;
use agentie_presa;

##Create table(s)

CREATE TABLE IF NOT EXISTS Admin
(id int unique auto_increment primary key,
username char(20),
parola BINARY(64),
logat tinyint(1)
);


CREATE TABLE IF NOT EXISTS Jurnalist
(id int unique auto_increment primary key,
username char(20),
nume char(20),
parola BINARY(32),
logat tinyint(1)
);

CREATE TABLE IF NOT EXISTS Articol
(id int unique auto_increment primary key,
titlu char(40),
abstract_articol char(144),
autor char(40),
continut text(3000)
);

CREATE TABLE IF NOT EXISTS articole_inrudite
(id int unique auto_increment primary key,
id_articol1 int,
id_articol2 int,
index(id_articol1),
index(id_articol2),
foreign key (id_articol1) references Articol(id),
foreign key (id_articol2) references Articol(id));

ALTER TABLE articol MODIFY continut text(3000);
