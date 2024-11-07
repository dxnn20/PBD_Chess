package com.pbd.PBD.Project.DTO;

import com.pbd.PBD.Project.Entity.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BestPlayerDTO {

    Player player;
    Long winCount;

}

