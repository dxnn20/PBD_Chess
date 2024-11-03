package com.pbd.PBD.Project.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tip", schema = "pbd")
public class Tip {
    @Id
    @Column(name = "ID_tip", nullable = false)
    private Integer id;

    @Size(max = 20)
    @Column(name = "Nume", length = 20)
    private String nume;

}