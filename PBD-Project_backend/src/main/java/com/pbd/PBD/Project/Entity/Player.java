package com.pbd.PBD.Project.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

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

    public Integer getAge() {
        if (birth_date != null) {
            // Convert the birth_date (java.sql.Date) to LocalDate
            LocalDate birthDate = birth_date.toLocalDate();
            LocalDate currentDate = LocalDate.now();

            // Calculate the period between the birthDate and the current date
            Period period = Period.between(birthDate, currentDate);

            // Return the age (in years)
            return period.getYears();
        }
        return null; // Return null if the birth_date is null
    }

}
