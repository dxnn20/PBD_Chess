package com.pbd.PBD.Project.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Table(name = "joc")
public class Joc {

    @Id
    @GeneratedValue
    @Column(name = "ID_joc")
    Integer id;

    @Column(name = "Data_inceput_joc")
    java.sql.Date startDate;

    @Column(name = "Data_sfarsit_joc")
    java.sql.Date endDate;

    @Column(name = "Tip_joc")
    private String type;

    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_tip")
    private Tip typeID;

    @JoinColumn(name = "Invingator")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Player winner;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Jucator1")
    private Player jucator1;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Jucator2")
    private Player jucator2;

    @Column(name = "Nr_partide")
    private Integer nrPartide;

    @Column(name = "Nr_partide_jucate")
    private Integer nrPartideJucate;

    @Column(name = "Scor_jucator1")
    private Integer scorJucator1;

    @Column(name = "Scor_jucator2")
    private Integer scorJucator2;

}
