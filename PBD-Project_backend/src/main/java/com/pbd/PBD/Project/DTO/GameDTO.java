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
    private String type;
    private Integer nrPartide;
//    private Integer id;
    private Integer jucator1, jucator2;
}
