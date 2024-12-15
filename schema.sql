CREATE DATABASE IF NOT EXISTS PBD;

DROP TABLE IF EXISTS tip;
DROP TABLE IF EXISTS Jucatori;
DROP TABLE IF EXISTS Joc;

CREATE TABLE Tip(
    ID_tip INT NOT NULL,
    Nume VARCHAR (20),

    CONSTRAINT tip_pk PRIMARY KEY (ID_tip)
);

CREATE TABLE Jucatori(
    ID_jucator INT NOT NULL AUTO_INCREMENT,
    nume VARCHAR (30),
    data_inscrierii DATE,
    data_nasterii DATE,

    CONSTRAINT jucatori_pk PRIMARY KEY(ID_jucator)
);

CREATE TABLE Joc(
    ID_joc INT NOT NULL AUTO_INCREMENT,
    ID_tip INT NOT NULL,
    Tip_joc VARCHAR(20),
    Jucator1 INT,
    Jucator2 INT,
    Nr_partide INT,
	Nr_partide_jucate INT,
    Data_inceput_joc DATE NOT NULL,
    Data_sfarsit_joc DATE,
    Scor_jucator1 INT,
    Scor_jucator2 INT,
    Invingator INT,

    CONSTRAINT joc_pk PRIMARY KEY(ID_joc),
    CONSTRAINT joc_fk FOREIGN KEY(ID_tip) REFERENCES Tip(Id_tip) on delete cascade,
    CONSTRAINT joc_fk1 FOREIGN KEY (Jucator1) REFERENCES Jucatori(ID_jucator) on delete cascade,
    CONSTRAINT joc_fk2 FOREIGN KEY (Jucator2) REFERENCES Jucatori(ID_jucator) on delete cascade,
    CONSTRAINT joc_fk3 FOREIGN KEY (Invingator) REFERENCES Jucatori(ID_jucator) on delete cascade, 
    -- CONSTRAINT idjoc_chk CHECK(ID_joc<1000000000), 
    CONSTRAINT partide_chk CHECK(Nr_partide<100),
    CONSTRAINT partidejuc_chk CHECK(Nr_partide_jucate<100),
    CONSTRAINT datainc_chk CHECK (Data_inceput_joc<Data_sfarsit_joc)
);

DELIMITER $$

CREATE TRIGGER future_date
BEFORE INSERT ON Jucatori
FOR EACH ROW
BEGIN
    IF NEW.data_nasterii > CURDATE() OR NEW.data_inscrierii > CURDATE() THEN
        SIGNAL SQLSTATE '45000' 
        SET MESSAGE_TEXT = 'Future dates are not allowed';
    END IF;
END$$

CREATE TRIGGER set_winner_and_end_date
BEFORE UPDATE ON Joc
FOR EACH ROW
BEGIN
    IF NEW.Nr_partide_jucate = NEW.Nr_partide THEN

        IF NEW.Scor_jucator1 > NEW.Scor_jucator2 THEN
            SET NEW.Invingator = NEW.Jucator1;
        ELSEIF NEW.Scor_jucator2 > NEW.Scor_jucator1 THEN
            SET NEW.Invingator = NEW.Jucator2;
        ELSE
            SET NEW.Invingator = NULL;
        END IF;


        SET NEW.Data_sfarsit_joc = NOW();
    END IF;
END$$

DELIMITER ;
