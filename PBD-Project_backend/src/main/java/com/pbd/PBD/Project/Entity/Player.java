package com.pbd.PBD.Project.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Getter
@Setter
@Table(name = "jucatori")
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "ID_jucator")
    @PrimaryKeyJoinColumn
    private Integer id;

    @Column(name = "data_inscrierii")
    private Date registration_date;

    @Column(name = "data_nasterii")
    private Date birth_date;

    @Column(name = "nume")
    private String name;

}
