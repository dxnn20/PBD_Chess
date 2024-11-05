package com.pbd.PBD.Project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "joc")
public class Joc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // Constructor for new games
    public Joc(java.sql.Date startDate, java.sql.Date endDate, String type, Tip typeID,
               Player winner, Player jucator1, Player jucator2, Integer nrPartide,
               Integer nrPartideJucate, Integer scorJucator1, Integer scorJucator2) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.typeID = typeID;
        this.winner = winner;
        this.jucator1 = jucator1;
        this.jucator2 = jucator2;
        this.nrPartide = nrPartide;
        this.nrPartideJucate = nrPartideJucate;
        this.scorJucator1 = scorJucator1;
        this.scorJucator2 = scorJucator2;
    }

    @Override
    public String toString(){
        return getNrPartide().toString();
    }

}
