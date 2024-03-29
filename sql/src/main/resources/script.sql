CREATE TABLE IF NOT EXISTS NATIONALITY
(
    ID smallint PRIMARY KEY NOT NULL IDENTITY,
    NAME varchar(60) NOT NULL
);

CREATE TABLE IF NOT EXISTS AUTHORS
(
    ID integer PRIMARY KEY NOT NULL IDENTITY,
    FIRSTNAME varchar(50) NOT NULL,
    LASTNAME varchar(50) NOT NULL,
    BIRTHDAY date NOT NULL,
    NATIONALITY smallint NOT NULL,
    CONSTRAINT AUTHORS_NATIONALITY_ID_FK FOREIGN KEY (NATIONALITY) REFERENCES NATIONALITY (ID)
);

CREATE TABLE IF NOT EXISTS BOOKS
(
    ID integer PRIMARY KEY NOT NULL IDENTITY,
    TITLE varchar(100) NOT NULL,
    AUTHOR integer NOT NULL,
    PUBLISHED date NOT NULL,
    STOCK integer NOT NULL,
    CONSTRAINT BOOKS_AUTHORS_ID_FK FOREIGN KEY (AUTHOR) REFERENCES AUTHORS (ID)
);

CREATE TABLE IF NOT EXISTS MEMBERS
(
    ID integer PRIMARY KEY NOT NULL IDENTITY,
    FIRSTNAME varchar(50) NOT NULL,
    LASTNAME varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS BORROWINGS
(
    ID integer PRIMARY KEY NOT NULL IDENTITY,
    BOOK_ID integer NOT NULL,
    MEMBER_ID integer NOT NULL,
    BORROW_DATE date NOT NULL,
    RETURN_DATE date NOT NULL,
    CONSTRAINT BORROWINGS_BOOKS_ID_FK FOREIGN KEY (BOOK_ID) REFERENCES BOOKS (ID),
    CONSTRAINT BORROWINGS_MEMBERS_ID_FK FOREIGN KEY (MEMBER_ID) REFERENCES MEMBERS (ID)
);



INSERT INTO NATIONALITY (ID, NAME) VALUES (0, 'Ukrainian');
INSERT INTO NATIONALITY (ID, NAME) VALUES (1, 'German');


INSERT INTO MEMBERS (ID, FIRSTNAME, LASTNAME) VALUES (0, 'Vova', 'Perebykivskyi');
INSERT INTO MEMBERS (ID, FIRSTNAME, LASTNAME) VALUES (1, 'Andriy', 'Khlyvnyuk');
INSERT INTO MEMBERS (ID, FIRSTNAME, LASTNAME) VALUES (2, 'Maksym', 'Goroshkevych');


INSERT INTO AUTHORS (ID, FIRSTNAME, LASTNAME, BIRTHDAY, NATIONALITY) VALUES (0, 'Taras', 'Shevchenko', '1814-03-09', 0);
INSERT INTO AUTHORS (ID, FIRSTNAME, LASTNAME, BIRTHDAY, NATIONALITY) VALUES (1, 'Friedrich', 'Nietzsche', '1844-10-15', 1);
INSERT INTO AUTHORS (ID, FIRSTNAME, LASTNAME, BIRTHDAY, NATIONALITY) VALUES (2, 'Erich Maria', 'Remarque', '1898-06-22', 1);


INSERT INTO BOOKS (ID, TITLE, AUTHOR, PUBLISHED, STOCK) VALUES (0, 'Kobzar', 0, '1840-01-01', 5);
INSERT INTO BOOKS (ID, TITLE, AUTHOR, PUBLISHED, STOCK) VALUES (1, 'The Birth of Tragedy', 1, '2018-01-01', 10);
INSERT INTO BOOKS (ID, TITLE, AUTHOR, PUBLISHED, STOCK) VALUES (2, 'The Dream Room', 2, '1920-01-01', 0);

INSERT INTO BORROWINGS (ID, BOOK_ID, MEMBER_ID, BORROW_DATE, RETURN_DATE) VALUES (0, 0, 1, '2018-01-01', '2018-01-31');
INSERT INTO BORROWINGS (ID, BOOK_ID, MEMBER_ID, BORROW_DATE, RETURN_DATE) VALUES (1, 1, 1, '2018-01-01', '2018-01-31');
INSERT INTO BORROWINGS (ID, BOOK_ID, MEMBER_ID, BORROW_DATE, RETURN_DATE) VALUES (2, 0, 0, '2018-01-02', '2018-01-04');

/* TRANSACTIONS */
UPDATE BOOKS SET BOOKS.STOCK = 1 WHERE BOOKS.ID = 2;
SELECT * FROM BOOKS;
ROLLBACK;

CREATE USER root PASSWORD root;
GRANT DBA TO root;