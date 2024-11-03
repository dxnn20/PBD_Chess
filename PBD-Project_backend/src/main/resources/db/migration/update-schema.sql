create TABLE joc
(
    id_joc            INT          NOT NULL,
    data_inceput_joc  date         NULL,
    data_sfarsit_joc  date         NULL,
    tip_joc           VARCHAR(255) NULL,
    id_tip            INT          NULL,
    invingator        INT          NULL,
    jucator1          INT          NULL,
    jucator2          INT          NULL,
    nr_partide        INT          NULL,
    nr_partide_jucate INT          NULL,
    scor_jucator1     INT          NULL,
    scor_jucator2     INT          NULL,
    CONSTRAINT pk_joc PRIMARY KEY (id_joc)
);

create TABLE jucatori
(
    id_jucator      INT          NOT NULL,
    data_inscrierii date         NULL,
    data_nasterii   date         NULL,
    nume            VARCHAR(255) NULL,
    CONSTRAINT pk_jucatori PRIMARY KEY (id_jucator)
);

alter table joc
    add CONSTRAINT uc_joc_id_tip UNIQUE (id_tip);

alter table joc
    add CONSTRAINT uc_joc_invingator UNIQUE (invingator);

alter table joc
    add CONSTRAINT FK_JOC_ON_ID_TIP FOREIGN KEY (id_tip) REFERENCES pbd.tip (id_tip);

alter table joc
    add CONSTRAINT FK_JOC_ON_INVINGATOR FOREIGN KEY (invingator) REFERENCES jucatori (id_jucator);

alter table joc
    add CONSTRAINT FK_JOC_ON_JUCATOR1 FOREIGN KEY (jucator1) REFERENCES jucatori (id_jucator) ON delete CASCADE;

alter table joc
    add CONSTRAINT FK_JOC_ON_JUCATOR2 FOREIGN KEY (jucator2) REFERENCES jucatori (id_jucator) ON delete CASCADE;

create TABLE pbd.tip
(
    id_tip INT         NOT NULL,
    nume   VARCHAR(20) NULL,
    CONSTRAINT pk_tip PRIMARY KEY (id_tip)
);
CREATE TABLE joc
(
    id_joc            INT          NOT NULL,
    data_inceput_joc  date         NULL,
    data_sfarsit_joc  date         NULL,
    tip_joc           VARCHAR(255) NULL,
    id_tip            INT          NULL,
    invingator        INT          NULL,
    jucator1          INT          NULL,
    jucator2          INT          NULL,
    nr_partide        INT          NULL,
    nr_partide_jucate INT          NULL,
    scor_jucator1     INT          NULL,
    scor_jucator2     INT          NULL,
    CONSTRAINT pk_joc PRIMARY KEY (id_joc)
);

CREATE TABLE jucatori
(
    id_jucator      INT          NOT NULL,
    data_inscrierii date         NULL,
    data_nasterii   date         NULL,
    nume            VARCHAR(255) NULL,
    CONSTRAINT pk_jucatori PRIMARY KEY (id_jucator)
);

ALTER TABLE joc
    ADD CONSTRAINT uc_joc_id_tip UNIQUE (id_tip);

ALTER TABLE joc
    ADD CONSTRAINT uc_joc_invingator UNIQUE (invingator);

ALTER TABLE joc
    ADD CONSTRAINT FK_JOC_ON_ID_TIP FOREIGN KEY (id_tip) REFERENCES pbd.tip (id_tip);

ALTER TABLE joc
    ADD CONSTRAINT FK_JOC_ON_INVINGATOR FOREIGN KEY (invingator) REFERENCES jucatori (id_jucator);

ALTER TABLE joc
    ADD CONSTRAINT FK_JOC_ON_JUCATOR1 FOREIGN KEY (jucator1) REFERENCES jucatori (id_jucator) ON DELETE CASCADE;

ALTER TABLE joc
    ADD CONSTRAINT FK_JOC_ON_JUCATOR2 FOREIGN KEY (jucator2) REFERENCES jucatori (id_jucator) ON DELETE CASCADE;

CREATE TABLE pbd.tip
(
    id_tip INT         NOT NULL,
    nume   VARCHAR(20) NULL,
    CONSTRAINT pk_tip PRIMARY KEY (id_tip)
);