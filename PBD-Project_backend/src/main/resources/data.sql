DELETE FROM jucatori;
DELETE FROM tip;
DELETE FROM joc;

-- Reset the auto-increment counter for jucatori
ALTER TABLE jucatori AUTO_INCREMENT = 1;

INSERT INTO jucatori (data_inscrierii, data_nasterii, nume) VALUES
('2023-01-15', '1995-04-20', 'Andrei Popescu'),
('2023-02-25', '1990-06-10', 'Maria Ionescu'),
('2023-03-10', '1988-11-05', 'George Mihai'),
('2023-04-05', '2000-01-15', 'Elena Cristea'),
('2023-05-20', '1992-08-30', 'Ion Vasilescu'),
('2023-06-17', '1994-12-12', 'Ana Georgescu'),
('2023-07-22', '1998-07-07', 'Dan Radu'),
('2023-08-30', '1985-05-25', 'Sorin Matei'),
('2023-09-14', '1991-09-21', 'Irina Stoica'),
('2023-10-11', '1993-03-18', 'Victor Alexandru');

ALTER TABLE tip AUTO_INCREMENT = 1;

-- Insert game types
INSERT INTO pbd.tip (ID_tip, Nume) VALUES
(1, 'Chess'),
(2, 'Checkers');

-- Insert games with only Chess and Checkers
INSERT INTO joc (Data_inceput_joc, Data_sfarsit_joc, Tip_joc, ID_tip, Invingator, Jucator1, Jucator2, Nr_partide, Nr_partide_jucate, Scor_jucator1, Scor_jucator2, ID_joc)
VALUES ('2024-01-01', '2024-01-02', 'Chess', 1, 1, 1, 2, 5, 0, 3, 2, 1);

INSERT INTO joc (Data_inceput_joc, Data_sfarsit_joc, Tip_joc, ID_tip, Invingator, Jucator1, Jucator2, Nr_partide, Nr_partide_jucate, Scor_jucator1, Scor_jucator2, ID_joc)
VALUES ('2024-01-03', '2024-01-04', 'Checkers', 2, 3, 3, 4, 3, 0, 2, 1, 2);

INSERT INTO joc (Data_inceput_joc, Data_sfarsit_joc, Tip_joc, ID_tip, Invingator, Jucator1, Jucator2, Nr_partide, Nr_partide_jucate, Scor_jucator1, Scor_jucator2, ID_joc)
VALUES ('2024-01-05', '2024-01-06', 'Chess', 1, 5, 5, 6, 3, 0, 6, 4, 3);

INSERT INTO joc (Data_inceput_joc, Data_sfarsit_joc, Tip_joc, ID_tip, Invingator, Jucator1, Jucator2, Nr_partide, Nr_partide_jucate, Scor_jucator1, Scor_jucator2, ID_joc)
VALUES ('2024-01-07', '2024-01-08', 'Checkers', 2, 7, 7, 8, 5, 0, 4, 3, 4);

INSERT INTO joc (Data_inceput_joc, Data_sfarsit_joc, Tip_joc, ID_tip, Invingator, Jucator1, Jucator2, Nr_partide, Nr_partide_jucate, Scor_jucator1, Scor_jucator2, ID_joc)
VALUES ('2024-01-09', '2024-01-10', 'Chess', 1, 9, 1, 10, 3, 0, 8, 6, 5);
