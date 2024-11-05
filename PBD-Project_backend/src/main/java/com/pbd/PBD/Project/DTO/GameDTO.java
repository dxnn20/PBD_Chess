package com.pbd.PBD.Project.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.ConstructorParameters;

@Setter
@Getter
@NoArgsConstructor
public class GameDTO {
    private String startDate;
    private String endDate;
    private Integer typeID;
    private String type;
    private Integer winner;
    private Integer nrPartide;
//    private Integer id;
    private Integer jucator1, jucator2;
    private Integer nrPartideJucate;
    private Integer scorJucator1;
    private Integer scorJucator2;
}
